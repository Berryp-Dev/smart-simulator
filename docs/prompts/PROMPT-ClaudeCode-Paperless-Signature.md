# Prompt per Claude Code — Paperless Signature + allineamenti

## Istruzioni generali (vincolanti)

- Applica **fedelmente** quanto indicato in questo prompt e in `ServicesSimulator-CODING-STANDARDS.md` (versione aggiornata allegata). **Non prendere decisioni autonome.**
- In caso di ambiguità o di caso non coperto, **fermati e chiedi**, invece di scegliere in autonomia. Se sei in auto-mode, segnala esplicitamente ogni scelta presa, per poterla rivedere.
- Rispetta in particolare: architettura esagonale package-by-feature (una cartella per categoria di artefatto, mai mista), identificatori in inglese e commenti/Javadoc in italiano, prefissi metodi (`get`/`insert`/`update`/`count`/`is`/`has`, altrimenti verbo coerente), enum con suffisso `Code`, port `UseCase`/`Port`, `Resource` (mai `Controller`), DTO `DtoReq`/`DtoResp`/`Dto`, validator dedicato che raccoglie **tutti** gli errori.
- Non modificare file di documentazione (`*.md`): vengono aggiornati a parte.

## Cambio di standard da applicare (vale per tutto il codice)

Ogni campo enum è un **enum anche nel DTO** (mai `String`). Il DTO usa un enum gemello in `adapter/in/rest/dto/enums`, con lo **stesso nome semplice** dell'enum di dominio; nel mapper uno dei due è **qualificato per esteso** (nome completo di package). Mapping con `switch` esplicito, mai `valueOf`. Il validator controlla solo la **presenza** (non null); il controllo "valore ammesso" non esiste più nei validator. Un valore non convertibile al tipo del campo (enum non valido, non numerico) è gestito automaticamente dall'handler del core (Task A).

## Task A — Core

1. **`RandomUtil`** in `com.capgemini.servicessimulator.core.util`, metodo `generateNumericString(int length)`: stringa di cifre numeriche random della lunghezza richiesta. Replica la logica **già usata** dal generatore locale di Configure (in particolare per gli zeri iniziali): non cambiarne il comportamento; se la logica esistente è ambigua, fermati e chiedi.
2. **`GlobalExceptionHandler`**: aggiungi la gestione di `HttpMessageNotReadableException` causata da un valore non convertibile al tipo del campo (causa `InvalidFormatException`), per **qualsiasi tipo target** (enum, numerico, ecc.). Risposta **HTTP 400** con `ApiError` contenente un solo `ValidationError`: `field` = percorso del campo ricavato dal path dell'eccezione (es. `scenarioCode`, `listDocuments[2]`); `message` = per un target enum, messaggio che elenca i valori ammessi; per gli altri tipi, messaggio che indica che il valore non è valido per il tipo atteso. Per tutte le altre cause di `HttpMessageNotReadableException` (es. JSON malformato) **lascia invariato** il comportamento attuale. Se il percorso del campo non è ricavabile in un caso, fermati e chiedi.

## Task B — Allineamento di Configure (`practiceconfigure`)

- Enum DTO gemello `PaperlessPracticeConfigureScenarioCode` in `adapter/in/rest/dto/enums`; il campo `scenarioCode` di `PaperlessPracticeConfigureDtoReq` diventa di quel tipo.
- Mapper: conversione enum DTO → enum di dominio con `switch` esplicito, enum di dominio qualificato per esteso.
- `PaperlessPracticeConfigureValidator`: il controllo su `scenarioCode` diventa solo "presente" (non null); rimuovi il controllo di appartenenza ai valori ammessi.
- Sostituisci i generatori random locali con `RandomUtil.generateNumericString` (`IdPratica` → 10 cifre, `DM_IdProcesso` → 3 cifre) e rimuovi le classi/metodi locali diventati inutilizzati.
- Il comportamento esterno di Configure (endpoint, response `paperlessStatus`, mappatura messaggio) resta **invariato**.

## Task C — Nuova feature `documentsignature`

Package: `com.capgemini.servicessimulator.paperless.documentsignature`, struttura esagonale completa.

**Scopo**: scrivere su IBM MQ un messaggio per ogni documento, con le informazioni compatibili con lo stato documento.

**Endpoint**: `POST /paperless/document/signature` in `PaperlessDocumentResource`. **Response void**: `ResponseEntity<Void>` con status `200`, nessun body. Swagger: documenta `200` senza schema e `400` con `ApiError`. Nessun `...DtoResp`, nessun `...Result`.

**Request** `PaperlessDocumentSignatureDtoReq` (tutti obbligatori):
- `practiceIdOmnichannel` (String)
- `scope` (String)
- `scenarioCode` (enum DTO `PaperlessDocumentSignatureScenarioCode`: `ALL_SIGNED`, `PARTIAL_SIGNATURE`, `SIGNATURE_ERROR`)
- `listDocuments` (`List<Long>`: elenco di id numerici dei documenti)

**Dominio**: enum `PaperlessDocumentSignatureScenarioCode` in `domain/enums` (gemello); modello radice `PaperlessDocumentSignature` (`practiceIdOmnichannel`, `scope`, `scenarioCode`, `documentIds` come `List<Long>`; nessun value object annidato); enum di dominio per lo stato documento `PaperlessDocumentSignatureStatusCode` (`FIRMATO`, `PARZIALE`, `ERRORE_FIRMA`) — se Configure rappresenta lo `Stato` in modo diverso, fermati e chiedi.

**Port/Service**: `NotifyPaperlessDocumentSignatureUseCase` (port/in, ritorna `void`); port/out `PublishDocumentStatusUpdatePort` con adapter MQ dedicato che usa il metodo **testo grezzo** di `MqPublisherService` (payload XML via JAXB, classe nel package `mq/message` della feature). Un producer/port/adapter dedicato, **non riusare** quelli di Configure.

**Validator** `PaperlessDocumentSignatureValidator` (tutti i controlli, tutti gli errori raccolti):
1. `practiceIdOmnichannel` presente (non blank)
2. `scope` presente (non blank)
3. `scenarioCode` presente (non null)
4. `listDocuments` presente **e non vuota**
5. ogni elemento di `listDocuments` presente (non null), errore sul campo `listDocuments[i]`

**Logica del service** (dopo validazione positiva; se invalida → `ValidationException`):
1. Genera `IdPratica` **una sola volta per chiamata** con `RandomUtil.generateNumericString(10)`, identico in tutti i messaggi.
2. Se `scenarioCode` è `PARTIAL_SIGNATURE` o `SIGNATURE_ERROR`: genera un intero casuale tra 1 e N (N = numero elementi di `listDocuments`), posizione 1-based dell'elemento anomalo (`deviantElementPosition`). Per `ALL_SIGNED` non serve.
3. Cicla su `listDocuments` in ordine (posizione corrente 1-based) e per ogni elemento calcola lo `Stato` con **una funzione comune** (`resolveDocumentStatus`: scenario, posizione corrente, posizione anomala):
   - `FIRMATO` se `ALL_SIGNED`, oppure se la posizione corrente è diversa da quella anomala
   - `PARZIALE` se `PARTIAL_SIGNATURE` e posizione corrente = anomala
   - `ERRORE_FIRMA` se `SIGNATURE_ERROR` e posizione corrente = anomala
4. Pubblica **un messaggio per documento**. Nessun rollback né gestione speciale: se la pubblicazione fallisce a metà loop l'eccezione si propaga.

**Messaggio XML**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ns1:InvioAggiornamentoStato xmlns:ns1="http://www.example.org/InvioAggiornamentoStato/"
                             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
<ns1:IdPratica></ns1:IdPratica>
<ns1:IdExternal></ns1:IdExternal>
<ns1:AmbitoPratica></ns1:AmbitoPratica>
<ns1:IdDoc></ns1:IdDoc>
<ns1:Stato></ns1:Stato>
<ns1:CodiceFiscale></ns1:CodiceFiscale>
</ns1:InvioAggiornamentoStato>
```

| Campo | Valore |
|---|---|
| `IdPratica` | stringa random di 10 cifre, una per chiamata |
| `IdExternal` | `practiceIdOmnichannel` |
| `AmbitoPratica` | `scope` |
| `IdDoc` | id numerico dell'elemento corrente di `listDocuments` |
| `Stato` | calcolato come sopra |
| `CodiceFiscale` | costante fissa `XXXXXXXXXXXXXXXX`, costante locale alla feature |

**Configurazione** (`application.yml` del modulo paperless): nuova chiave `queueMap` dedicata `paperlessDocumentSignature` → coda `DEV.QUEUE.2`, broker `nhMqBroker`.

## Al termine

Elenca i file creati/modificati e segnala esplicitamente ogni scelta presa per ambiguità.
