# SmartSimulator — Specifiche dei servizi REST

File unico e cumulativo con la specifica di dettaglio di **ogni servizio
REST** implementato nel progetto, sottosistema per sottosistema. Da
aggiornare ad ogni nuovo servizio o correzione — una sezione per servizio.
Standard di codice/architettura comuni a tutti i servizi:
`SmartSimulator-CODING-STANDARDS.md`. Stato generale del progetto:
`SmartSimulator-PROJECT-CONTEXT.md`.

> **Nota sui path**: i path degli endpoint riportati in questo file sono
> relativi al context-path `/smart-simulator` dell'app (es.
> `POST /paperless/practice/configure` risponde su
> `/smart-simulator/paperless/practice/configure`).

---

## Sottosistema: Paperless

### Servizio: Practice Configure

**Stato**: implementato, con correzioni applicate (vedi changelog in
fondo alla sezione).

**Endpoint**: `POST /paperless/practice/configure`

**Package**: `com.capgemini.smart.simulator.paperless.practice.configure`
(area `practice`, API `configure`); `RestResource`:
`PaperlessPracticeConfigureRestResource` con `@Tag(name = "Practice")`.

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
`valueOf` sul nome della costante, qualificando per esteso l'enum di dominio):
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
`PaperlessDocumentSignatureRestResource` con `@Tag(name = "Document")` (package
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
`domain/enums` e `adapter/in/rest/dto/enums`; mapper con `valueOf`):
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

**Service**: `NotifyPaperlessDocumentSignatureService` (port/in, implementazione
`NotifyPaperlessDocumentSignatureServiceImpl`), ritorna `void`.

---

## Sottosistema: Document Viewer

### Servizio: Static Doc (retrieve)

**Stato**: implementato; verifica manuale del `200` con un PDF reale da
eseguire (i PDF vanno inseriti nelle risorse del modulo, vedi sotto).

**Struttura a due livelli (deroga dichiarata solo per questa API)**:
sottosistema → API, **senza livello area**, perché l'URL richiesto è
`/document-viewer/staticdoc`. Package radice
`com.capgemini.smart.simulator.documentviewer`, con `config/` e `staticdoc/`
(domain, application, adapter). L'API si chiama `staticdoc` perché `static` è
una parola riservata di Java e non può essere un package. Il livello
`dynamic`/`dynamicdoc` è previsto in futuro per un altro servizio e non esiste
ancora. Nessuna cartella `common`. Il servizio segue per il resto lo standard
di progetto (nessuna deroga `@ReplicatesOriginalBehavior`).

**Endpoint**: `GET /document-viewer/staticdoc?productCode=<valore>`
(con context-path: `/smart-simulator/document-viewer/staticdoc`).
Classe `DocumentViewerStaticDocRestResource` (`@Tag(name = "Document")`).

**Parametri**: `productCode` è un query param (`required = false` a livello
Spring: la presenza è controllata dal validator, non da Spring).

**Flusso**: `RestResource` → mapper (query param →
`DocumentSearchCriteria`, null-safe, senza validare) →
`RetrieveDocumentService.getDocument` → il service
chiama prima `RetrieveDocumentValidator` (se non valido lancia
`ValidationException` del core), poi `LoadDocumentPort`; se la port
non trova il documento lancia `DocumentNotFoundException`. La port
restituisce `Optional<Document>` (productCode, content `byte[]`).

**Validazione** (raccoglie tutti gli errori; campo = nome del contratto REST):

1. `productCode` presente e non blank: `Campo obbligatorio`;
2. `productCode`, se presente, solo caratteri alfanumerici (`^[A-Za-z0-9]+$`):
   `Valore non valido: sono ammessi solo caratteri alfanumerici`.

**Risposta**:

| Esito | Status | Body |
|---|---|---|
| Successo | 200 | contenuto del PDF; `Content-Type: application/pdf`; `Content-Disposition: inline; filename="<productCode>.pdf"` |
| Validazione fallita | 400 | `ApiError` con `errors` (`GlobalExceptionHandler` del core) |
| Documento non trovato | 404 | `ApiError` con un solo errore `field: null`, `message: Documento non trovato` |

Il 404 è gestito da un advice limitato alla RestResource
(`DocumentViewerStaticDocExceptionHandler`,
`@RestControllerAdvice(assignableTypes = DocumentViewerStaticDocRestResource.class)`
con `@Order(HIGHEST_PRECEDENCE)` per precedere il `GlobalExceptionHandler`,
`adapter/in/rest/advice`); `ApiError` popolato come per i 400 del core (solo
`status` ed `error` diversi); il `message` di primo livello è
`Risorsa non trovata: 1 errore/i`.

**Provenienza dei PDF**: in tutti gli ambienti i PDF stanno nelle risorse del
classpath del modulo, in `src/main/resources/pdf/static/<productCode>.pdf` (il
livello `static` è specifico della funzione `staticdoc`), e finiscono nel jar:
per aggiungere un PDF si rifà il build. Nessun file di configurazione per
ambiente.

**Adapter `adapter/out/classpath`** (dettaglio del servizio,
`DocumentClasspathAdapter`, implementa la port):

- legge la cartella del classpath indicata da `products-folder` (relativa alla
  radice del classpath; slash iniziali/finali eventuali vengono tolti);
- cerca il file **elencando** i `.pdf` della cartella
  (`ResourcePatternResolver`, pattern `classpath:<products-folder>/*.pdf`) e
  confrontando il nome in modo **esatto e case-sensitive** con
  `<productCode>.pdf` (su Windows `target/classes` ignora le maiuscole, il jar
  no: il confronto esplicito vale in entrambi i casi);
- nessun percorso è costruito a partire da `productCode`: si confronta solo il
  nome; la difesa resta la regex alfanumerica del validator;
- cartella assente o vuota, o file non trovato: `Optional.empty()` (quindi
  404); errori di I/O: `UncheckedIOException` (500);
- il contenuto è restituito come `byte[]`;
- nessuna decisione di business: la conversione verso HTTP è nella RestResource.

**Configurazione** (`@ConfigurationProperties(prefix =
"smart-simulator.document-viewer")`, classe `DocumentViewerProperties` in
`config/`, valore obbligatorio con validazione fail-fast), file unico nel
modulo in `src/main/resources`, importato da `application.yml` dell'app con
`spring.config.import` (come quello di Paperless):

| File | Chiave | Valore |
|---|---|---|
| `document-viewer-config.yml` | `products-folder` | `pdf/static` |

La chiave è unica perché la futura funzione `dynamic` avrà una propria chiave
(`pdf/dynamic/`, non ancora creata).

