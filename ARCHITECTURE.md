# ARCHITECTURE - Convenzioni per i moduli sottosistema

Documento di sintesi per la creazione dei moduli sottosistema (es.
`smart-simulator-paperless`). Il dettaglio completo di tutte le regole è in
`SmartSimulator-CODING-STANDARDS.md`, che prevale in caso di divergenza.

Il modulo `smart-simulator-core` NON segue l'architettura esagonale: resta un
livello infrastrutturale condiviso.

## 0. Struttura dei moduli e avvio

- **Un solo modulo avviabile**: `smart-simulator-app`, con l'unica classe
  `@SpringBootApplication` (package radice `com.capgemini.smart.simulator`),
  l'unico `spring-boot-maven-plugin` e l'unico `application.yml` generale.
  Dipende da `smart-simulator-core` e da tutti i moduli sottosistema; un solo
  processo espone i servizi di tutti i sottosistemi.
- `smart-simulator-core` e ogni modulo sottosistema sono **solo librerie**:
  nessuna classe main, nessun `spring-boot-maven-plugin`, nessun
  `application.yml`.
- Configurazione: ogni sottosistema ha un file dedicato con nome univoco
  (es. `paperless-config.yml`, in `src/main/resources`), importato
  dall'`application.yml` dell'app via `spring.config.import`, più un file
  per ciascun ambiente (`paperless-config-dev.yml`,
  `paperless-config-sit.yml`) con i valori che dipendono dall'ambiente
  (broker, code, topic). I valori comuni (porta, datasource, connessione IBM
  MQ, bootstrap Kafka) stanno nell'app, mai nel core: quelli uguali ovunque
  nell'`application.yml`, quelli per ambiente in `application-<env>.yml`.
  Le chiavi di `queueMap`/`topicMap`/`brokerConfigMap` devono essere uniche
  tra i sottosistemi e identiche in ogni ambiente.
- Ambienti: profili Spring `dev` e `sit`, attivati solo dall'esterno. Nessun
  profilo di default: senza un profilo ammesso l'app non parte (controllo
  nel modulo app). Credenziali nei file di ambiente. Dettaglio in
  `SmartSimulator-CODING-STANDARDS.md`, "Configurazione per ambiente".

## 1. Struttura a pacchetti (esagonale, sottosistema → area → API)

Ogni modulo sottosistema è organizzato su tre livelli, coerenti con la forma
dell'endpoint `/<sottosistema>/<area>/<api>`: **sottosistema → area → API
(feature)**. Ogni API ha la propria struttura esagonale completa ed è
autosufficiente. Il dettaglio completo è in `SmartSimulator-CODING-STANDARDS.md`.

```
com.capgemini.smart.simulator.<sottosistema>/
├── config/                          wiring Spring del modulo
├── common/                          condivisione liv. 3 (tra aree) — solo se serve
├── <area>/                          area (es. practice, document)
│   ├── common/                      condivisione liv. 2 (tra API dell'area) — solo se serve
│   └── <api>/                       API / feature (es. configure, signature)
│       ├── domain/                  model, model/component, enums, exception
│       ├── application/             port/in, port/out, service, validation
│       └── adapter/                 in/rest (resource, dto, dto/model, dto/enums, mapper),
│                                    out (persistence, kafka, mq/message)
```

| Pacchetto (nell'API) | Ruolo |
|---|---|
| `domain/model` | Entità/value object radice di dominio. |
| `domain/model/component` | Value object annidati dell'API (nessun suffisso `Dto`). |
| `domain/enums` | Enum di dominio (suffisso `Code`). |
| `domain/exception` | `DomainValidationException`, solo se l'API ha invarianti di dominio. |
| `application/port/in` | Casi d'uso (driving port), suffisso `UseCase`, naming verbo+oggetto con intento di business. |
| `application/port/out` | Dipendenze verso l'esterno (driven port), suffisso `Port`. Solo tipi di dominio o primitivi: mai JAXB, DTO o payload di messaggi. |
| `application/service` | Implementazione dei casi d'uso: prima il validator, poi la business logic tramite le port out. |
| `application/validation` | Un validator dedicato per ogni scenario/operazione (punto 3). |
| `adapter/in/rest/resource` | `Resource` dell'API, suffisso `Resource` (mai `Controller`), nome `<Sottosistema><Area><Api>Resource`, con `@Tag(name = "<Area>")`. |
| `adapter/in/rest/dto` | DTO radice, suffissi `DtoReq`/`DtoResp`. |
| `adapter/in/rest/dto/model` | DTO annidati, suffisso `Dto`. |
| `adapter/in/rest/dto/enums` | Enum gemelli degli enum di dominio (stesso nome semplice). |
| `adapter/in/rest/mapper` | Mapping DTO <-> dominio. |
| `adapter/in/soap` | Endpoint SOAP (CXF, contract-first). |
| `adapter/out/persistence` | Adapter JDBC (`JdbcTemplate`, SQL scritto a mano, schema esplicito nell'SQL) che implementano le port/out di persistenza. Nessun Hibernate/JPA. |
| `adapter/out/kafka` | Adapter che riusano `KafkaPublisherService` del core. |
| `adapter/out/mq` | Adapter che implementano le port/out e riusano `MqPublisherService` del core; `message/` contiene il payload pubblicato (es. classi JAXB). |

`config/` resta a livello di **modulo** (non di feature): wiring Spring e binding
port -> adapter.

**Condivisione (`common`)**: si crea solo quando serve (artefatto usato da una
seconda API della stessa area, o da una seconda area), con la stessa struttura
delle API e solo ciò che è davvero condiviso. Livelli: API → `<area>/common` →
`common` del sottosistema → core. Un'API non dipende mai da un'altra API,
nemmeno della stessa area.

**Regola di dipendenza:** `domain` e `application` NON devono dipendere da
framework o librerie esterne (Spring escluso dove strettamente necessario per
le annotazioni sui bean). Gli `adapter` possono usare framework e librerie. Le
dipendenze puntano sempre verso l'interno (adapter -> application -> domain).
Il modello di dominio resta separato dai DTO REST: l'application layer non vede
mai il DTO HTTP.

**Port in uscita:** la `port/out` non importa mai classi dell'adapter (JAXB,
DTO, payload). Accetta solo tipi di dominio o primitivi; la conversione nel
payload avviene solo nell'adapter, campo per campo. I valori fissi del messaggio
legacy stanno nell'adapter; le regole di business restano nel service.

## 2. Convenzioni di naming e codice

- Identificatori (classi, metodi, variabili, parametri) in **inglese**;
  commenti/Javadoc in **italiano**.
- Codice leggibile, funzioni brevi, una responsabilità per funzione.
- Nomi di variabili/parametri coerenti con lo scopo semantico (no `data`, `obj`, `temp`).
- Prefissi dei metodi:
  - `get` recupera dati
  - `insert` inserisce dati (inclusi i metodi builder-style, es. `insertError`, mai `addError`)
  - `update` aggiorna dati
  - `count` determina un contatore
  - `is` / `has` restituiscono un booleano
  - ogni altro caso: un verbo coerente con lo scopo (`validate`, `publish`, `resolve`, `parse`, `format`, `generate`, ...)
- Tutti gli **enum** hanno suffisso `Code` (es. `PaperlessPracticeConfigureScenarioCode`).
- **Enum nei DTO**: ogni campo enum del dominio è un enum anche nel DTO (mai
  `String`), con enum gemello in `adapter/in/rest/dto/enums` e stesso nome
  semplice; nel mapper l'enum di dominio è qualificato per esteso e la
  conversione è un `switch` esplicito (mai `valueOf`).

## 3. Validazione e gestione errori

- Ogni scenario/operazione REST ha il proprio validator in `application/validation`,
  che implementa `ScenarioValidator<T>` del core.
- Il validator raccoglie TUTTI gli errori (non fail-fast) tramite
  `ValidationResult` / `ValidationError` (`core/validation`). Per i campi enum
  controlla solo la **presenza**.
- Il service applicativo chiama il validator come primo passo; se
  `isValid()` è false solleva `ValidationException` (`core/exception`),
  altrimenti procede con la business logic.
- `GlobalExceptionHandler` risponde HTTP 400 con `ApiError` contenente la lista
  completa degli errori. Un valore non convertibile al tipo del campo DTO (enum
  non ammesso, valore non numerico) fallisce in deserializzazione, prima del
  validator: l'handler del core risponde 400 con un solo `ValidationError`
  (percorso del campo, es. `scenarioCode` o `listDocuments[2]`), non aggregato
  con gli errori del validator.
- Un corpo non leggibile (JSON malformato, body assente, struttura di tipo
  sbagliato) dà 400 con `ApiError` e messaggio generico; le eccezioni non
  gestite restano 500.
- Le violazioni di invarianti di dominio usano `DomainValidationException`
  locale alla feature, intercettata da un `@ControllerAdvice` scoped al
  `Resource`.

## 4. Codice comune

- Funzioni di utilizzo trasversale a tutto il progetto vanno nel core
  (`core/util`: `StringUtil`, `DateTimeUtil`, `CollectionUtil`, `NumberUtil`,
  `JsonUtil`, `RandomUtil`).
- Codice condiviso da più funzioni dello stesso modulo sottosistema va estratto
  in classi di supporto dentro il modulo stesso: non si sale al core per logica
  specifica di un solo sottosistema.

## 5. Deroghe allo standard: `@ReplicatesOriginalBehavior`

Alcuni servizi devono replicare fedelmente interfaccia e comportamento di un
sistema esterno reale simulato, invece di seguire questo standard. È una
eccezione DICHIARATA: si usa solo quando la specifica riporta
"Comportamento: REPLICA ORIGINALE (sistema: ..., rif: ...)", mai per scelta
libera dell'implementatore.

- Si annota il `Resource` (o il singolo metodo, se la deroga riguarda una sola
  operazione) con `@ReplicatesOriginalBehavior(system = "...", reference = "...")`
  (`core/annotation`); `reference` (WSDL/doc/specifica) è opzionale.
  L'annotazione rende le deroghe cercabili con grep/IDE.
- Contratto REST (DTO request/response, naming campi, status code, formato
  errori) e logica di validazione (es. fail-fast) seguono il sistema originale
  e possono discostarsi dallo standard.
- Isolamento: la gestione errori specifica va in un `@RestControllerAdvice`
  scoped al `Resource` (`@ControllerAdvice(assignableTypes = ...)`), che ha
  priorità su `GlobalExceptionHandler` per quel `Resource`.
- La deviazione resta confinata in `adapter/in/*`: NON va propagata ai layer
  `domain` e `application`, che continuano a seguire lo standard.
