# SmartSimulator — Specifiche dei servizi REST

File unico e cumulativo con la specifica di dettaglio di **ogni servizio
REST** implementato nel progetto, sottosistema per sottosistema. Da
aggiornare ad ogni nuovo servizio o correzione — una sezione per servizio.
Standard di codice/architettura comuni a tutti i servizi:
`SmartSimulator-CODING-STANDARDS.md`. Stato generale del progetto:
`SmartSimulator-PROJECT-CONTEXT.md`.

---

## Sottosistema: Paperless

### Servizio: Practice Configure

**Stato**: implementato, con correzioni applicate (vedi changelog in
fondo alla sezione).

**Endpoint**: `POST /paperless/practice/configure`

**Package**: `com.capgemini.smart.simulator.paperless.practice.configure`
(area `practice`, API `configure`); `Resource`:
`PaperlessPracticeConfigureResource` con `@Tag(name = "Practice")`.

**Comportamento**: standard di progetto (nessuna deroga
`@ReplicatesOriginalBehavior`).

**Request DTO**: `PaperlessPracticeConfigureDtoReq`

| Campo | Tipo | Obbligatorio | Note |
|---|---|---|---|
| `practiceIdOmnichannel` | String | Sì | solo controllo di presenza, nessun controllo di formato |
| `customerDenomination` | String | Sì | solo controllo di presenza, nessun controllo di formato |
| `scenarioCode` | enum DTO `PaperlessPracticeConfigureScenarioCode` | Sì | enum gemello del dominio in `adapter/in/rest/dto/enums`; il valore non ammesso è rifiutato automaticamente (vedi Validazione) |
| `scope` | String | Sì | solo controllo di presenza, nessun controllo di formato; valorizza `AmbitoPratica` nel messaggio MQ |

**Response**: `200 OK` con body `PaperlessPracticeConfigureDtoResp`
(lo status HTTP 200 è quello reale della risposta, **non** è un campo
del body).

**Response DTO**: `PaperlessPracticeConfigureDtoResp`

| Campo | Tipo | Note |
|---|---|---|
| `paperlessStatus` | String | lo `Stato` italiano calcolato dalla mappatura `scenarioCode` → `Stato` (vedi sotto), identico a quello inviato nel messaggio `InvioAggiornamentoStato`; **non** è lo `scenarioCode` inglese della request |

Esempio di response (`scenarioCode = SIGNATORIES_WAITING`):

```json
{
  "paperlessStatus": "ATTESA_FIRMATARI"
}
```

**Validazione** (`PaperlessPracticeConfigureValidator`): esegue tutti i
controlli e raccoglie tutti gli errori (non fail-fast):
1. `practiceIdOmnichannel` presente
2. `customerDenomination` presente
3. `scenarioCode` presente (non null)
4. `scope` presente

Se la validazione fallisce → `ValidationException` → HTTP 400 con
`ApiError` (lista completa errori). Se ha successo → procede con la
business logic.

**Valore non ammesso di `scenarioCode`**: non è controllato dal validator.
La deserializzazione Jackson lo rifiuta e `GlobalExceptionHandler` (core)
risponde HTTP 400 con `ApiError` e un solo `ValidationError` (campo
`scenarioCode`, messaggio con i valori ammessi), non aggregato con gli
altri errori (vedi CODING-STANDARDS.md, "Gestione errori").

**Enum — `PaperlessPracticeConfigureScenarioCode`** (stesso nome semplice
in `domain/enums` e in `adapter/in/rest/dto/enums`; il mapper converte con
`switch` esplicito, qualificando per esteso l'enum di dominio):
- `SIGNATORIES_WAITING`
- `PRACTICE_LOCKED`
- `PRACTICE_UNLOCKED`
- `CONTEXT_SIGNATURE_WAITING`
- `CUSTOMER_SIGNATURE_WAITING`

**Logica di business**: dopo validazione positiva, costruisce un
messaggio XML `InvioAggiornamentoStato` (JAXB) e lo pubblica su **IBM MQ**
(testo grezzo, non JSON — vedi CODING-STANDARDS.md sezione "Trasporto MQ")
tramite port/out dedicata (`PublishStatusUpdatePort`) e relativo adapter.
La port accetta solo un oggetto di dominio (`PaperlessPracticeStatusUpdate`:
id pratica, id processo, id esterno, ambito, motivo blocco, stato); la
conversione nel messaggio JAXB avviene solo nell'adapter MQ. Il testo di
`MotivoBlocco` è calcolato nel service.
Al termine ritorna al chiamante un risultato di dominio
(`PaperlessPracticeConfigureResult`) con lo `Stato` calcolato, che il
mapper converte in `PaperlessPracticeConfigureDtoResp`.

**Configurazione broker/coda** (configurazione del sottosistema paperless —
oggi `application.yml` del modulo `smart-simulator-paperless`, dopo la
ristrutturazione `paperless-config.yml` —, chiave `queueMap` dedicata
`paperlessPracticeConfigure`):
- coda: `DEV.QUEUE.2`
- broker: `nhMqBroker`

> Nota: il servizio Paperless Signature scrive su una coda diversa
> (`DEV.QUEUE.1`), con una propria port/adapter/chiave `queueMap`
> dedicata (regola "un producer per funzionalità" — non riutilizza quelli
> di Configure).

**Enum di dominio — `PaperlessPracticeStatusCode`** (`domain/enums`): `ATTESA_FIRMATARI`,
`PRATICA_BLOCCATA`, `PRATICA_SBLOCCATA`, `ATTESA_CONTESTO_FIRMA`,
`ATTESA_FIRMA_CLIENTE`. È lo `Stato` calcolato dal service; il risultato di
dominio (`PaperlessPracticeConfigureResult`) lo porta come enum e il mapper lo
converte nella `String` `paperlessStatus` della response (JSON invariato).

**Mappatura campi del messaggio `InvioAggiornamentoStato`**:

| Campo XML | Valorizzazione |
|---|---|
| `IdPratica` | stringa random di 10 cifre numeriche, generata ad ogni chiamata |
| `DM_IdProcesso` | stringa random di 3 cifre numeriche, generata ad ogni chiamata |
| `IdExternal` | = `practiceIdOmnichannel` della request |
| `AmbitoPratica` | = `scope` della request (**non più un default di configurazione**: `DefaultsResolver` rimosso per questo campo) |
| `MotivoBlocco` | valorizzato **solo se** `scenarioCode = PRACTICE_LOCKED`, con testo `"Firmatario " + customerDenomination + " privo di consenso privacy"`; per ogni altro `scenarioCode` il campo non è valorizzato (null/assente) |
| `Stato` | mappato da `scenarioCode`, vedi tabella sotto |

**Mappatura `scenarioCode` → `Stato`**:

| scenarioCode | Stato |
|---|---|
| `SIGNATORIES_WAITING` | `ATTESA_FIRMATARI` |
| `PRACTICE_LOCKED` | `PRATICA_BLOCCATA` |
| `PRACTICE_UNLOCKED` | `PRATICA_SBLOCCATA` |
| `CONTEXT_SIGNATURE_WAITING` | `ATTESA_CONTESTO_FIRMA` |
| `CUSTOMER_SIGNATURE_WAITING` | `ATTESA_FIRMA_CLIENTE` |

**Generazione valori random** (`IdPratica`, `DM_IdProcesso`): tramite
`RandomUtil.generateNumericString(int length)` del core
(`core.util`), con lunghezza 10 per `IdPratica` e 3 per `DM_IdProcesso`.
I generatori locali alla feature sono stati rimossi.

**Changelog della specifica**:
- *Versione iniziale*: `AmbitoPratica` valore fisso `"SSL"` da
  `DefaultsResolver`; `MotivoBlocco` valorizzato su `SIGNATORIES_WAITING`.
- *Correzione applicata*: aggiunto campo obbligatorio `scope` in request,
  che ora valorizza `AmbitoPratica` (rimosso `DefaultsResolver` per questo
  campo); `MotivoBlocco` corretto per scattare su `PRACTICE_LOCKED`
  (non più `SIGNATORIES_WAITING`).
- *Integrazione response*: l'endpoint ritorna `PaperlessPracticeConfigureDtoResp`
  con il campo `paperlessStatus` (lo `Stato` della mappatura
  `scenarioCode` → `Stato`) invece di nessun body; lo status HTTP resta
  `200` e non compare come campo del body.
- *Allineamento enum*: `scenarioCode` diventa enum nel DTO (enum gemello
  `PaperlessPracticeConfigureScenarioCode` in `dto/enums`) invece di
  `String`; il validator controlla solo la presenza; il valore non ammesso
  è gestito dall'handler del core. Generatori random sostituiti da
  `RandomUtil` del core.

---

### Servizio: Signature

**Stato**: implementato e verificato (test da Swagger OK: scenari
`ALL_SIGNED`, `PARTIAL_SIGNATURE`, `SIGNATURE_ERROR`, errori di
validazione e di deserializzazione, messaggi su `DEV.QUEUE.1`).

**Endpoint**: `POST /paperless/document/signature`, in
`PaperlessDocumentSignatureResource` con `@Tag(name = "Document")` (package
`com.capgemini.smart.simulator.paperless.document.signature`, area
`document`, API `signature`).

**Scopo**: scrivere su IBM MQ un messaggio per ogni documento, con le
informazioni compatibili con lo stato documento.

**Comportamento**: standard di progetto (nessuna deroga
`@ReplicatesOriginalBehavior`).

**Request DTO**: `PaperlessDocumentSignatureDtoReq`

| Campo | Tipo | Obbligatorio | Note |
|---|---|---|---|
| `practiceIdOmnichannel` | String | Sì | solo controllo di presenza |
| `scope` | String | Sì | solo controllo di presenza; valorizza `AmbitoPratica` |
| `scenarioCode` | enum DTO `PaperlessDocumentSignatureScenarioCode` | Sì | enum gemello del dominio in `dto/enums` |
| `listDocuments` | `List<Long>` | Sì | elenco di id numerici dei documenti; non vuota; nessun elemento null |

**Response**: `200 OK` **void**, nessun body (`ResponseEntity<Void>`). Nessun
`DtoResp`. Swagger: `200` senza schema, `400` con `ApiError`.

**Enum — `PaperlessDocumentSignatureScenarioCode`** (stesso nome semplice in
`domain/enums` e `adapter/in/rest/dto/enums`; mapper con `switch`):
- `ALL_SIGNED` — firma cliente, tutti i documenti con firma finale
- `PARTIAL_SIGNATURE` — firma cliente, 1 documento con firma parziale
- `SIGNATURE_ERROR` — firma, 1 documento con firma in errore

**Enum di dominio — `PaperlessDocumentSignatureStatusCode`**: `FIRMATO`,
`PARZIALE`, `ERRORE_FIRMA`.

**Validazione** (`PaperlessDocumentSignatureValidator`, tutti gli errori,
non fail-fast):
1. `practiceIdOmnichannel` presente (non blank)
2. `scope` presente (non blank)
3. `scenarioCode` presente (non null)
4. `listDocuments` presente e non vuota
5. ogni elemento di `listDocuments` presente (non null), errore sul campo
   `listDocuments[i]` con messaggio "Campo obbligatorio"

Un valore non convertibile al tipo del campo (`scenarioCode` non
ammesso, elemento di `listDocuments` non numerico) non passa dal
validator: `GlobalExceptionHandler` (core) risponde HTTP 400 con
`ApiError` e un solo `ValidationError` (campo es. `scenarioCode` o
`listDocuments[2]`), non aggregato con gli altri errori.

**Logica di business** (dopo validazione positiva):
1. Genera `IdPratica` **una sola volta per chiamata**
   (`RandomUtil.generateNumericString(10)`), uguale in tutti i messaggi.
2. Se `scenarioCode` è `PARTIAL_SIGNATURE` o `SIGNATURE_ERROR`: genera un
   intero casuale tra 1 e N (N = numero di elementi di `listDocuments`),
   posizione 1-based dell'elemento anomalo (`deviantElementPosition`).
   Per `ALL_SIGNED` non serve.
3. Cicla su `listDocuments` in ordine e per ogni elemento calcola lo
   `Stato` con una funzione comune (scenario, posizione corrente,
   posizione anomala), poi pubblica **un messaggio XML per documento**.
4. Nessun rollback: se la pubblicazione fallisce a metà loop l'eccezione
   si propaga.

**Calcolo `Stato`**:

| Condizione | Stato |
|---|---|
| `ALL_SIGNED`, oppure posizione corrente diversa da quella anomala | `FIRMATO` |
| `PARTIAL_SIGNATURE` e posizione corrente = anomala | `PARZIALE` |
| `SIGNATURE_ERROR` e posizione corrente = anomala | `ERRORE_FIRMA` |

**Messaggio** `InvioAggiornamentoStato` (JAXB, namespace
`http://www.example.org/InvioAggiornamentoStato/`, pubblicato come testo
grezzo):

| Campo XML | Valorizzazione |
|---|---|
| `IdPratica` | stringa random di 10 cifre, una per chiamata |
| `IdExternal` | = `practiceIdOmnichannel` |
| `AmbitoPratica` | = `scope` |
| `IdDoc` | id numerico dell'elemento corrente di `listDocuments` (campo JAXB di tipo `Long`) |
| `Stato` | calcolato come sopra |
| `CodiceFiscale` | costante `XXXXXXXXXXXXXXXX` (costante locale alla feature) |

**Configurazione** (configurazione del sottosistema paperless, come per
Configure): chiave `queueMap` dedicata `paperlessDocumentSignature`, coda
`DEV.QUEUE.1`, broker `nhMqBroker` (lo stesso di Configure). Port/out dedicata
`PublishDocumentStatusUpdatePort` e relativo adapter (non riusa quelli di
Configure). La port accetta solo un oggetto di dominio
(`PaperlessDocumentStatusUpdate`: id pratica, id esterno, ambito, id
documento, stato); la conversione nel messaggio JAXB e il `CodiceFiscale`
fisso sono nell'adapter MQ.

**Use case**: `NotifyPaperlessDocumentSignatureUseCase` (port/in), ritorna
`void`.

---

## Sottosistema: Account — Raccordo Partitario

**Stato**: modulo creato con le interfacce autogenerate dagli YAML. **`create` è
implementato; gli altri 11 servizi sono non implementati** e rispondono HTTP 501
(vedi sotto).
L'implementazione avviene un servizio per volta, con una sezione di
specifica per ciascuno da aggiungere qui.

**Modulo**: `smart-simulator-account-raccordo-partitario`; package radice
`com.capgemini.smart.simulator.account.raccordopartitario`.

**Comportamento**: REPLICA ORIGINALE (sistema: Raccordo Partitario Conti, rif:
nome del file YAML). Le `Resource` sono annotate con
`@ReplicatesOriginalBehavior(system = "Raccordo Partitario Conti", reference =
"<file yaml>")`. Contratto al 100% dello YAML; vedi ARCHITECTURE.md sezione 6 e
CODING-STANDARDS "Moduli con interfacce autogenerate da YAML".

**YAML sorgente** (in
`smart-simulator-app/src/main/resources/openapi/account-raccordo-partitario/`):

| File | Contenuto |
|---|---|
| `components-v1.2.2.yaml` | Non è un'API: schemi comuni (`Header`, `ErrorResponse`, `SrvAcntKey`, `Currency`, ...) e response `Error300`...`Error511`. Nessuna execution propria. |
| `raccordopartitario_sales.yaml` | 11 operazioni POST, tag `Sales` → interfaccia `SalesApi`, `Resource` `AccountRaccordoPartitarioSalesResource` (package `...sales.adapter.in.rest`). |
| `raccordopartitario_sales_currentaccounts_configure.yaml` | 1 operazione POST, tag `Current-Account` → `CurrentAccountApi`, `Resource` `AccountRaccordoPartitarioCurrentAccountConfigureResource` (package `...currentaccount.configure.adapter.in.rest`). |

**Operazioni** (tutte POST):

| operationId | Path | Successo |
|---|---|---|
| `canBeCreated` | `/smartcatalog/raccordo/can-be-created` (così nello YAML; il suo changelog parla di `smartengine`, si segue lo YAML) | 201 |
| `create` | `/smartengine/raccordo/create` | 201 (implementato) |
| `reserve` | `/smartengine/raccordo/reserve` | 200 |
| `activate` | `/smartengine/raccordo/activate` | 201 |
| `getMainInfo` | `/smartengine/raccordo/getmaininfo` | 200 |
| `getExternalKey` | `/smartengine/raccordo/getexternalkey` | 200 |
| `setSettlementServiceAccountKey` | `/smartengine/raccordo/setsettlementserviceaccountkey` | 201, senza body |
| `getOthSrvActns` | `/smartengine/raccordo/getothsrvactns` | 200 |
| `getAdditionalAttributes` | `/smartengine/raccordo/getadditionalattributes` | 200 |
| `setFinalNdg` | `/smartengine/raccordo/setfinalndg` | 201, senza body |
| `cancel` | `/smartengine/raccordo/cancel` | 201, senza body |
| `CurrentAccountConfigure` | `/ui-app/raccordo/sales/current-accounts/configure` | 204 |

**Gestione errori** (advice scoped alle due `Resource`, busta dello YAML
`{header, payload{mainError{errorCode, errorMessage, technicalDescription},
errorList[]}}`):

| Caso | Status | `errorMessage` | `technicalDescription` |
|---|---|---|---|
| Bean validation, `ConstraintViolationException` | 400 | `Bad Request.` (description di `Error400` nello YAML) | percorso del campo + messaggio (default), es. `payload.caseID: must not be null` |
| Enum o valore non convertibile | 400 | `Bad Request.` | percorso del campo + `Invalid value: '<v>'` (o `Invalid value`) |
| JSON malformato, body vuoto, struttura di tipo errato | 400 | `Bad Request.` | `Request body is invalid or malformed` (preceduto dal percorso del campo se noto) |
| Servizio non implementato | 501 | `Not implemented.` (description di `Error501`) | `Service not implemented` (fallback: lo YAML non ha testo tecnico) |

- `errorCode` = status HTTP come stringa (default, non ricavato dallo YAML).
- `errorList` contiene tutte le violazioni, ordinate per percorso del campo;
  `mainError` coincide con la prima.
- `header`: riecheggiato dalla richiesta, senza i campi null, se il body è
  leggibile e lo contiene; altrimenti generato con `timestamp` e `trackId`.
- Messaggi di bean validation in inglese per le sole richieste del modulo
  (interceptor sul tipo del handler).

**Note sul generato**:
- Per ogni YAML con `paths` esiste una execution; gli schemi comuni sono generati
  due volte (uno per package) e springdoc li espone una volta per nome.
- Il template `pojo.mustache` evita le proprietà inventate (`tlement...`): vedi
  README e CODING-STANDARDS.

**Persistenza**: nessuna per ora. Lo schema DB sarà uno solo per l'intero
modulo (non uno per area/API), da definire quando un servizio ne avrà bisogno.

### Servizio: Create (`create`)

**Endpoint**: POST `/smartengine/raccordo/create` (`SalesApi.create`), successo 201.
Esagono nel package `...raccordopartitario.sales.create` (`domain/model`,
`application/port/in`, `application/service`, `adapter/in/rest/mapper`); la
`Resource` Sales resta in `sales/adapter/in/rest/resource`.

**Request** (`CreateDtoRequest`: `header` + `payload{caseID, srvActnData}`): i campi
usati sono `payload.srvActnData.bankCod` e `payload.srvActnData.srvCod` (String,
opzionali). L'obbligatorietà e i vincoli sono quelli dell'interfaccia (bean
validation dello YAML, es. `bankCod` di 5 caratteri → 400 con la busta dello
YAML): nessun validator applicativo.

**Response 201** (`CreateDtoResponse`: solo `srvAcntKey` e `statusInfo`):

| Campo | Valore |
|---|---|
| `srvAcntKey.bankCod` | `payload.srvActnData.bankCod` della request |
| `srvAcntKey.srvCod` | `payload.srvActnData.srvCod` della request |
| `srvAcntKey.key` | UUID generato a ogni chiamata (`UUID.randomUUID()`) |
| `statusInfo.status` | `CREATED` (enum generato) |
| `statusInfo.sStatus` | `Creato` |
| `statusInfo.tsStatus` | istante corrente, UTC, troncato ai millisecondi |

- **Nessun `header` nella response**: il contratto non lo dichiara, quindi non
  si gestisce né si aggiunge alcun header (nemmeno HTTP).
- **Formato di `tsStatus`**: ISO-8601 UTC prodotto dall'`ObjectMapper` di Spring,
  che può omettere gli zeri finali dei millisecondi (es. `2026-10-06T23:21:55.13Z`).
- **`bankCod`/`srvCod` assenti** (o `payload`/`srvActnData` assenti): 201 senza
  errori; il valore assente è riportato assente (`null`, come serializzano i
  modelli generati) in `srvAcntKey`.
- **Nessuna persistenza**: servizio senza stato.
- **Errori**: advice invariato (header della request riecheggiato, `timestamp`/
  `trackId` generati solo se la request non è leggibile).

