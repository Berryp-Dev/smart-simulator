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

> **Log di request/response (tutti i servizi)**: ogni chiamata REST è
> tracciata a livello INFO dal filtro `RestLoggingFilter` del core, senza
> alcun intervento sui `RestResource` e senza cambiare i contratti: una
> riga `Richiesta REST` (metodo, URI con query string, `Content-Type`, body
> completo) e una riga `Risposta REST` (status, `Content-Type`, durata, body
> completo se testuale). Gli header non sono loggati; `OPTIONS`, Swagger UI,
> `/v3/api-docs` e `/actuator/**` sono esclusi. Le righe di una stessa chiamata (anche quelle
> MQ) portano lo stesso `requestId`. Dettagli in
> `SmartSimulator-CODING-STANDARDS.md`, sezione «Logging».

---

## Sottosistema: Paperless

### Servizio: Practice Configure

**Stato**: implementato, con correzioni applicate (vedi changelog in
fondo alla sezione).

**Endpoint**: `POST /paperless/practice/configure`

**Package**: `com.capgemini.smart.simulator.paperless.practice.configure`
(area `practice`, API `configure`); `RestResource`:
`PaperlessPracticeConfigureRestResource` con `@Tag(name = "Paperless / Practice")`.

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
**Log di scrittura MQ**: la scrittura su `DEV.QUEUE.2` è tracciata dal core
(`MqPublisherService`) con righe `Scrittura MQ` prima (coda e payload XML
completo), dopo (esito positivo) e in caso di errore (ERROR con stack
trace); vedi CODING-STANDARDS.md, sezione «Logging». Nessun cambiamento di
contratto.
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
`PaperlessDocumentSignatureRestResource` con `@Tag(name = "Paperless / Document")` (package
`com.capgemini.smart.simulator.paperless.document.signature`, area
`document`, API `signature`).

**Scopo**: scrivere su IBM MQ un messaggio per ogni documento, con le
informazioni compatibili con lo stato documento.

**Log di scrittura MQ**: la scrittura su `DEV.QUEUE.1` è tracciata dal core
(`MqPublisherService`): per **ciascun** messaggio una riga `Scrittura MQ`
prima (coda e payload XML completo) e una dopo (esito positivo), oppure una
riga ERROR con stack trace in caso di errore (l'eccezione si propaga come
prima, senza rollback). Vedi CODING-STANDARDS.md, sezione «Logging». Nessun
cambiamento di contratto.

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

### Servizio: getStatic

**Stato**: implementato; verifica manuale del `200` con un PDF reale da
eseguire (i PDF vanno inseriti nelle risorse del modulo, vedi sotto).

**Struttura** (standard: sottosistema → area → API): sottosistema
`document-viewer` → area `document` → API `getstatic`, con l'esagono dentro
l'API. Package radice `com.capgemini.smart.simulator.documentviewer`, con
`config/`, `common/` e `document/getstatic/{domain,application,adapter}`. Il
`common` di modulo contiene `domain/exception/DocumentNotFoundException`;
`Document` e `GetStaticDocumentSearchCriteria` stanno nel `domain/model`
dell'API `getstatic`. Le classi specifiche del servizio hanno nome
`GetStatic...`, le altre (RestResource, mapper e advice) hanno nome
`<Sottosistema><Area><Api>`. Una funzione `dynamic` è prevista in futuro per un
altro servizio e non esiste ancora. Il servizio segue lo standard di progetto
(nessuna deroga `@ReplicatesOriginalBehavior`).

**Endpoint**: `GET /document-viewer/document/getStatic?productCode=<valore>`
(con context-path: `/smart-simulator/document-viewer/document/getStatic`).
Classe `DocumentViewerDocumentGetStaticRestResource` (`@Tag(name = "Document Viewer / Document")`),
metodo `getStatic`; l'`operationId` Swagger (`getStatic`) coincide con il metodo
e con l'ultimo segmento del path.

**Parametri**: `productCode` è un query param (`required = false` a livello
Spring: la presenza è controllata dal validator, non da Spring).

**Log**: la risposta `200` è `application/pdf`, quindi il filtro di log del
core registra solo status, `Content-Type`, lunghezza in byte e durata: il
**contenuto del PDF non è loggato** (le risposte 400/404, in JSON, sono
loggate per intero). Nessun cambiamento di contratto.

**Flusso**: `RestResource` → mapper (`DocumentViewerDocumentGetStaticMapper`, query
param → `GetStaticDocumentSearchCriteria`, null-safe, senza validare) →
`RetrieveGetStaticDocumentService.getStaticDocument` → il service
(`RetrieveGetStaticDocumentServiceImpl`) chiama prima
`RetrieveGetStaticDocumentValidator` (se non valido lancia
`ValidationException` del core), poi `LoadGetStaticDocumentPort`; se la port
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
(`DocumentViewerDocumentGetStaticExceptionHandler`,
`@RestControllerAdvice(assignableTypes = DocumentViewerDocumentGetStaticRestResource.class)`
con `@Order(HIGHEST_PRECEDENCE)` per precedere il `GlobalExceptionHandler`,
`adapter/in/rest/advice`); `ApiError` popolato come per i 400 del core (solo
`status` ed `error` diversi); il `message` di primo livello è
`Risorsa non trovata: 1 errore/i`.

**Provenienza dei PDF**: in tutti gli ambienti i PDF stanno nelle risorse del
classpath del modulo, in `src/main/resources/pdf/static/<productCode>.pdf` (il
livello `static` è specifico del servizio `getStatic`), e finiscono nel jar:
per aggiungere un PDF si rifà il build. Nessun file di configurazione per
ambiente.

**Adapter `adapter/out/classpath`** (dettaglio del servizio,
`GetStaticDocumentClasspathAdapter`, implementa la port):

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

---

### Servizio: getGenerated

**Stato**: implementato (non ci sono ancora documenti generati reali: la
port in uscita restituisce sempre lo stesso PDF generico).

**Struttura**: sottosistema `document-viewer` → area `document` (la stessa di
`getStatic`) → API `getgenerated`, esagono completo e autosufficiente.
Package radice `com.capgemini.smart.simulator.documentviewer.document.getgenerated`.
Nessuna deroga `@ReplicatesOriginalBehavior`.

**Endpoint**: `GET /document-viewer/document/getGenerated?appName=...&trackingId=...&callUnseal=...&bnlToken=...`
(con context-path: `/smart-simulator/document-viewer/document/getGenerated`).
Classe `DocumentViewerDocumentGetGeneratedRestResource` (`@Tag(name = "Document Viewer / Document")`),
metodo `getGenerated`; l'`operationId` Swagger (`getGenerated`) coincide con il
metodo e con l'ultimo segmento del path.

**Parametri** (tutti query param, `required = false` a livello Spring: la
presenza è controllata dal validator, non da Spring):

| Parametro | Tipo contratto | Tipo a livello REST/dominio | Note |
|---|---|---|---|
| `appName` | String | String | solo controllo di presenza |
| `trackingId` | UUID | String | presenza + formato UUID controllati dal validator (non binding Spring su `UUID`, per restare nel percorso di errore standard del progetto invece di un 500 non gestito) |
| `callUnseal` | String | String | solo controllo di presenza |
| `bnlToken` | String | String | solo controllo di presenza; decriptato lato service |

**Flusso**: `RestResource` → mapper (`DocumentViewerDocumentGetGeneratedMapper`,
query param → `GetGeneratedDocumentCriteria`, null-safe, senza validare) →
`RetrieveGetGeneratedDocumentService.getGeneratedDocument` → il service
(`RetrieveGetGeneratedDocumentServiceImpl`) chiama prima
`RetrieveGetGeneratedDocumentValidator` (se non valido lancia
`ValidationException` del core), poi `DecryptBnlTokenPort` (decripta
`bnlToken` restituendo l'id del documento), poi `LoadGeneratedDocumentPort`
con l'id decriptato; se la port non trova il documento lancia
`DocumentNotFoundException`. La port restituisce `Optional<GeneratedDocument>`
(documentId, content `byte[]`).

**Validazione** (`RetrieveGetGeneratedDocumentValidator`, raccoglie tutti gli
errori; campo = nome del contratto REST):

1. `appName` presente: `Campo obbligatorio`;
2. `trackingId` presente: `Campo obbligatorio`; se presente ma non è un UUID
   valido (`UUID.fromString`): `Valore non valido: formato UUID atteso`;
3. `callUnseal` presente: `Campo obbligatorio`;
4. `bnlToken` presente: `Campo obbligatorio`.

**Logica di business**: dopo validazione positiva, il service decripta
`bnlToken` tramite `DecryptBnlTokenPort` → adapter `BnlTokenDecryptionAdapter`
(`adapter/out/decryption`), che chiama `URLEncUtils.decodeAES(bnlToken, key)`
della libreria `com.capgemini:encryption` con la chiave AES configurata
(`token-decryption-key`). Il valore decriptato identifica l'id del documento
da recuperare; al momento non esistono documenti reali, quindi
`LoadGeneratedDocumentPort` (adapter `GeneratedDocumentClasspathAdapter`,
`adapter/out/classpath`) ignora l'id e restituisce sempre lo stesso PDF
generico, letto dal classpath del modulo in
`<generated-products-folder>/<default-generated-name>`.

**Risposta**:

| Esito | Status | Body |
|---|---|---|
| Successo | 200 | contenuto del PDF; `Content-Type: application/pdf`; `Content-Disposition: inline; filename="<documentId>.pdf"` |
| Validazione fallita | 400 | `ApiError` con `errors` (`GlobalExceptionHandler` del core) |
| Documento non trovato | 404 | `ApiError` con un solo errore `field: null`, `message: Documento non trovato` |

Il 404 è gestito da un advice limitato alla RestResource
(`DocumentViewerDocumentGetGeneratedExceptionHandler`,
`@RestControllerAdvice(assignableTypes = DocumentViewerDocumentGetGeneratedRestResource.class)`
con `@Order(HIGHEST_PRECEDENCE)`, stessa forma del corrispondente advice di
`getStatic`), riusa `DocumentNotFoundException` (`common` del modulo).

**Dipendenza aggiunta** (pom di `smart-simulator-document-viewer`):

```xml
<dependency>
    <groupId>com.capgemini</groupId>
    <artifactId>encryption</artifactId>
    <version>1.0.0</version>
</dependency>
```

**Provenienza del PDF generico**: in tutti gli ambienti sta nelle risorse del
classpath del modulo, in `src/main/resources/pdf/generated/<default-generated-name>`,
e finisce nel jar: per sostituirlo si rifà il build. Nessun file di
configurazione per ambiente.

**Configurazione** (aggiunta a `DocumentViewerProperties` / `document-viewer-config.yml`,
tutti i valori obbligatori con validazione fail-fast):

| File | Chiave | Valore |
|---|---|---|
| `document-viewer-config.yml` | `generated-products-folder` | `pdf/generated` |
| `document-viewer-config.yml` | `default-generated-name` | `GENERATED.PDF` |
| `document-viewer-config.yml` | `token-decryption-key` | `000102030405060708090a0b0c0d0e0f101112131415161718191a1b1c1d1e1f` |


---

## Sottosistema: Raccordo Partitario Conti

**Comportamento**: REPLICA ORIGINALE (sistema: Raccordo Partitario Conti, rif: gli YAML OpenAPI in
`smart-simulator-app/src/main/resources/openapi/raccordo-partitario-conti/`:
`raccordopartitario_sales.yaml`, `raccordopartitario_sales_currentaccounts_configure.yaml` e i componenti
comuni `components-v1.2.2.yaml`). Il contratto esterno (path, metodo, request/response, nomi dei campi JSON,
enum, status code, envelope degli errori) segue lo YAML al 100%; gli standard di progetto non si applicano al
contratto ma solo all'architettura interna. Ogni `RestResource` è annotato con
`@ReplicatesOriginalBehavior(system = "Raccordo Partitario Conti", reference = "<file YAML>")`.

**Struttura**: modulo `smart-simulator-raccordo-partitario-conti`, package radice
`com.capgemini.smart.simulator.raccordopartitario.conti`, area unica `raccordo` con una API per operazione
(`<area>/<api>`: `canbecreated`, `create`, `reserve`, `activate`, `getmaininfo`, `getexternalkey`,
`setsettlementserviceaccountkey`, `getothsrvactns`, `getadditionalattributes`, `setfinalndg`, `cancel`,
`currentaccountconfigure`). Tag Swagger: `Raccordo Partitario Conti / Raccordo`. Nessuna persistenza, nessun
MQ/Kafka, nessun file di configurazione dedicato.

**Stato**: tutti i servizi sono **non implementati**. Ogni `ServiceImpl` lancia subito
`ServiceNotImplementedException` (dominio, `common/domain/exception`), senza validazione né logica; nessuna
annotazione di bean validation sui DTO (la validazione sarà aggiunta con l'implementazione di ciascun servizio).

**Risposta 501** (da `Error501` dello YAML): `Content-Type: application/json`, body
`{"mainError":{"errorMessage":"Servizio non implementato: <operationId>"}}` (`ErrorResponse`/`ServiceErrorDto`;
`errorCode`, `technicalDescription` ed `errorList` omessi perché lo YAML non definisce valori per questo caso).
Lo YAML non prevede header di response. La risposta è prodotta da un `@RestControllerAdvice` limitato al package
del modulo (`RaccordoPartitarioContiServiceNotImplementedExceptionHandler`, `@Order(HIGHEST_PRECEDENCE)`).
Gli altri percorsi di errore (JSON malformato, valore enum non ammesso, path inesistente, metodo non supportato,
ecc.) restano del `GlobalExceptionHandler` del core.

| operationId | Endpoint | YAML | Status di successo (YAML) | Stato |
|---|---|---|---|---|
| `canBeCreated` | POST `/smartcatalog/raccordo/can-be-created` | `raccordopartitario_sales.yaml` | 201 | non implementato (501) |
| `create` | POST `/smartengine/raccordo/create` | `raccordopartitario_sales.yaml` | 201 | non implementato (501) |
| `reserve` | POST `/smartengine/raccordo/reserve` | `raccordopartitario_sales.yaml` | 200 | non implementato (501) |
| `activate` | POST `/smartengine/raccordo/activate` | `raccordopartitario_sales.yaml` | 201 | non implementato (501) |
| `getMainInfo` | POST `/smartengine/raccordo/getmaininfo` | `raccordopartitario_sales.yaml` | 200 | non implementato (501) |
| `getExternalKey` | POST `/smartengine/raccordo/getexternalkey` | `raccordopartitario_sales.yaml` | 200 | non implementato (501) |
| `setSettlementServiceAccountKey` | POST `/smartengine/raccordo/setsettlementserviceaccountkey` | `raccordopartitario_sales.yaml` | 201 (senza body) | non implementato (501) |
| `getOthSrvActns` | POST `/smartengine/raccordo/getothsrvactns` | `raccordopartitario_sales.yaml` | 200 | non implementato (501) |
| `getAdditionalAttributes` | POST `/smartengine/raccordo/getadditionalattributes` | `raccordopartitario_sales.yaml` | 200 | non implementato (501) |
| `setFinalNdg` | POST `/smartengine/raccordo/setfinalndg` | `raccordopartitario_sales.yaml` | 201 (senza body) | non implementato (501) |
| `cancel` | POST `/smartengine/raccordo/cancel` | `raccordopartitario_sales.yaml` | 201 (senza body) | non implementato (501) |
| `CurrentAccountConfigure` | POST `/ui-app/raccordo/sales/current-accounts/configure` | `raccordopartitario_sales_currentaccounts_configure.yaml` | 204 (senza body) | non implementato (501) |

Tutte le operazioni accettano un body JSON (`application/json`); le request di `raccordopartitario_sales.yaml`
hanno la forma `{ "header": {...}, "payload": {...} }` (`header` è un oggetto del body, non un header HTTP).
