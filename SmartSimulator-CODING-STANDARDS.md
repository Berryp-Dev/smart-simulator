# SmartSimulator — Standard di codice e architettura

Documento di riferimento per tutte le convenzioni concordate. Da applicare
in ogni nuovo modulo/servizio, e da citare per esteso nei prompt preparati
per Claude Code. Vedi anche `SmartSimulator-PROJECT-CONTEXT.md` (stato
del progetto) e `SmartSimulator-SERVICES-SPECIFICATION.md` (specifiche
di dettaglio di ogni servizio).

> SmartSimulator è il prototipo di riferimento per standardizzare i
> futuri progetti a microservizi con architettura esagonale. Ogni
> decisione di naming/organizzazione/pattern va presa pensando alla
> replicabilità come default futuro, non solo alla correttezza locale.

## Architettura esagonale — Package by Area e Feature

Il modulo `smart-simulator-core` resta un livello infrastrutturale
condiviso (NON segue questa struttura). Core e moduli sottosistema sono
**solo librerie** (vedi "Struttura dei moduli e avvio").

Ogni modulo sottosistema (es. `smart-simulator-paperless`) è organizzato
su **tre livelli gerarchici**: **sottosistema → area → API (feature)**,
coerenti con la forma dell'endpoint `/<sottosistema>/<area>/<api>` (es.
`/paperless/practice/configure`). Ogni API ha la propria struttura
esagonale **completa** (domain/application/adapter) ed è **autosufficiente**:
tutto ciò che la riguarda sta in una sola cartella. Un package `config/` a
livello di modulo resta cross-feature.

### Struttura standard (template per ogni sottosistema, area e API)

```
com.capgemini.smart.simulator.<sottosistema>/
├── config/                          wiring Spring del modulo
│
├── common/                          CONDIVISIONE LIV. 3 (tra aree) — solo se serve
│   ├── domain/model/component/      value object di dominio condivisi tra aree
│   └── adapter/in/rest/dto/model/   DTO annidati condivisi tra aree
│
├── <area_1>/                        AREA (es. practice)
│   ├── common/                      CONDIVISIONE LIV. 2 (tra API dell'area) — solo se serve
│   │   ├── domain/model/component/  value object condivisi tra 2+ API dell'area
│   │   └── adapter/in/rest/dto/model/  DTO annidati condivisi tra 2+ API dell'area
│   │
│   └── <api_1>/                     API / FEATURE (es. configure) — autosufficiente
│       ├── domain/
│       │   ├── model/               entità/value object RADICE di dominio
│       │   ├── model/component/     value object annidati dell'API (nessun suffisso "Dto")
│       │   ├── enums/               enum di dominio (suffisso "Code" obbligatorio)
│       │   └── exception/           DomainValidationException (solo se l'API ha invarianti)
│       │
│       ├── application/
│       │   ├── port/in/             driving port, interfaccia con suffisso "Service", verbo+oggetto
│       │   │                        (es. ConfigurePaperlessPracticeService)
│       │   ├── port/out/            driven port, suffisso "Port", naming = dipendenza
│       │   │                        tecnica; solo tipi di dominio o primitivi Java
│       │   ├── service/             implementazione, suffisso "ServiceImpl"
│       │   └── validation/          un validator dedicato per scenario
│       │
│       └── adapter/
│           ├── in/rest/
│           │   ├── resource/        RestResource dell'API, suffisso "RestResource" (MAI "Controller"),
│           │   │                    con @Tag dell'area (vedi sotto)
│           │   ├── dto/             DTO radice, suffissi "DtoReq"/"DtoResp"
│           │   ├── dto/model/       DTO annidati dell'API, suffisso "Dto" semplice
│           │   ├── dto/enums/       enum gemelli degli enum di dominio
│           │   └── mapper/          mapping DTO <-> dominio
│           └── out/
│               ├── persistence/     adapter JDBC (JdbcTemplate), schema esplicito nell'SQL
│               ├── kafka/           adapter che usa KafkaPublisherService del core
│               └── mq/              adapter che implementa la port/out e usa MqPublisherService;
│                   └── message/     payload pubblicato (es. classi JAXB)
│
└── <area_2>/                        AREA (es. document) — struttura speculare
    ├── common/
    └── <api_2>/                     (domain / application / adapter)
```

Una cartella per categoria di artefatto, **mai mista**: una classe REST
entry-point non condivide la cartella con i DTO; i DTO non condividono la
cartella con i loro enum; un adapter in uscita non condivide la cartella
con il payload del messaggio che pubblica.

### Regole di condivisione (`common`)

- La cartella `common` si crea **solo quando serve**: nel momento in cui un
  artefatto è usato da una seconda API della stessa area (liv. 2) o da una
  seconda area (liv. 3). Non si creano cartelle vuote in anticipo.
- Un `common` contiene la stessa struttura delle API, ma **solo** ciò che è
  davvero condiviso (value object, DTO annidati, e se serve anche una port, un
  mapper, ecc.).
- **Livelli di condivisione**: API → `<area>/common` → `common` del
  sottosistema → core (`core/util`, codice trasversale a tutto il progetto).
- **Dipendenze**: un'API può usare il `common` della propria area e quello del
  sottosistema, ma **mai le classi di un'altra API**, nemmeno della stessa
  area.

### Regole di dipendenza e confini tra layer

- `domain` e `application` non devono dipendere da framework/librerie esterne
  (Spring escluso dove strettamente necessario per le annotazioni sui bean, `@Autowired` incluso).
  Gli `adapter` possono dipendere liberamente da framework/librerie.
- **Domain vs DTO**: il modello di dominio resta separato dai DTO REST. Il
  `RestResource` converte il DTO in un oggetto di dominio tramite il **mapper**
  dell'API (`adapter/in/rest/mapper`) e invoca la porta in ingresso passando
  **solo il dominio**: né lo use case né il validator né il service ricevono o
  importano mai un DTO. Il validator lavora sul tipo di dominio. Il mapper
  **non valida e non lancia eccezioni per valori mancanti**: converte anche i
  null (campo assente -> null nel dominio, es. enum), perché la validazione
  formale resta nel validator. I nomi dei campi riportati negli errori di
  validazione sono quelli del contratto REST (es. `listDocuments[2]`), anche se
  nel dominio il campo ha un altro nome.
- **Port in uscita (inversione di dipendenza)**: ogni `port/out` appartiene
  all'application layer e **non importa mai** classi dell'adapter: né JAXB, né
  DTO, né payload di messaggi (es. `InvioAggiornamentoStato`). Accetta e
  restituisce solo **tipi di dominio o valori primitivi Java**. La conversione
  dominio -> payload (JAXB/JSON) avviene **esclusivamente nell'adapter** in
  uscita, che traduce campo per campo senza prendere decisioni di business.
  I valori fissi che sono un dettaglio del messaggio legacy (es. il
  `CodiceFiscale` costante) vivono nell'adapter; le regole di business (es. il
  testo di `MotivoBlocco`) restano nel service, che le passa alla port come
  campi del tipo di dominio.

### Documentazione Swagger per area

Ogni classe `RestResource` riporta `@Tag(name = "<Area>")` (es. `Practice`,
`Document`), così gli endpoint sono raggruppati per area nella Swagger UI.

## Convenzioni di naming

- **Nome del progetto — quattro forme, ciascuna col proprio ruolo**:
  - `smart-simulator`: moduli Maven (`smart-simulator-app`, `-core`, `-paperless`),
    jar, container, volume, cartelle, prefissi di configurazione
    (YAML e `@ConfigurationProperties`, es. `smart-simulator.defaults.<sottosistema>`),
    `spring.application.name`;
  - `com.capgemini.smart.simulator`: package Java e groupId Maven;
  - `smart_simulator`: database, utente e schema tecnico Postgres
    (identificatore valido senza virgolette negli SQL);
  - `SmartSimulator`: classi (es. `SmartSimulatorApplication`), titoli e
    nomi dei file di documentazione.
- **Lingua**: identificatori (classi, metodi, variabili, parametri) sempre
  in **inglese**. Commenti/Javadoc in italiano.
- **Leggibilità**: codice pulito, funzioni il più possibile ridotte, una
  responsabilità per funzione.
- **Variabili/parametri**: naming sempre coerente con lo scopo semantico
  (mai nomi generici tipo "data", "obj", "temp").
- **Modelli di dominio** (`domain/model`): **nessun suffisso tecnico nel nome
  delle classi di dominio** (non si usano né `Domain` né `Dto`; i suffissi
  `DtoReq`/`DtoResp`/`Dto` restano invece obbligatori per i DTO REST, vedi
  sotto). L'entità/value object radice si chiama con il concetto di business (es.
  `PaperlessPracticeConfiguration`, `PaperlessDocumentSignature`,
  `PaperlessPracticeStatusUpdate`). Il nome include area e API quando il
  concetto è specifico di un'API.
- **Enum**: suffisso obbligatorio **`Code`**
  (es. `PaperlessPracticeConfigureScenarioCode`).
- **Prefissi metodi/funzioni**:
  | Prefisso | Uso |
  |---|---|
  | `get` | funzioni che recuperano dati |
  | `insert` | funzioni che inseriscono dati (**inclusi i metodi builder-style**, es. `insertError`, non `addError` — nessuna eccezione per l'idioma Java "add") |
  | `update` | funzioni che aggiornano dati |
  | `count` | funzioni che determinano un contatore |
  | `is` / `has` | funzioni che restituiscono un booleano |
  | *(altro)* | verbo comunque coerente con lo scopo specifico (es. `validate`, `publish`, `resolve`, `parse`, `format`, `configure`) |
- **Port in/out**: suffissi diversi per ruolo, non solo per direzione —
  `port/in` usa suffisso **`Service`** (verbo+oggetto, intento di
  business) per l'interfaccia, mentre `application/service` usa suffisso
  **`ServiceImpl`** per l'implementazione (es. `ConfigurePaperlessPracticeService`
  e `ConfigurePaperlessPracticeServiceImpl`); `port/out` usa suffisso **`Port`** (dipendenza tecnica)
- **REST entry-point**: suffisso classe **`RestResource`** (il package resta `resource`), mai
  `Controller`; un `RestResource` per **API**, nome =
  `<Sottosistema><Area><Api>RestResource` (es.
  `PaperlessPracticeConfigureRestResource`, `PaperlessDocumentSignatureRestResource`),
  con `@Tag(name = "<Area>")`
- **DTO**: root request/response con suffisso `DtoReq`/`DtoResp`; DTO
  annidati/composti con solo `Dto`; un DTO non usa mai direttamente un
  enum di dominio — ha un proprio enum gemello con mapping nel mapper tramite
  `valueOf` sul nome della costante (vedi "Mapping degli enum"), stesso principio di
  disaccoppiamento già applicato a DTO vs modello di dominio. **Ogni
  campo enum del dominio è un enum anche nel DTO** (mai `String`): il DTO
  usa il proprio enum gemello, in `adapter/in/rest/dto/enums`, con lo
  stesso nome semplice dell'enum di dominio; nel mapper uno dei due è
  qualificato per esteso (nome completo di package). Il validator di
  scenario controlla solo la **presenza** (campo non null); il controllo
  "valore ammesso" è automatico (deserializzazione Jackson + handler del
  core, vedi "Gestione errori").
- **Enum annidati in un DTO**: naming
  `<NomeScenarioSenzaDtoReq><Concetto>Code`

## Mapping degli enum

- La conversione tra un enum dell'adapter (enum gemello del DTO REST o enum
  del modello generato) e l'enum di dominio, in entrambe le direzioni, si
  esegue con `valueOf` sul nome della costante, mai con `switch`. Esempio:
  `PaperlessDocumentSignatureScenarioCode.valueOf(request.getPayload().getScenarioCode().name())`.
- Le costanti dei due enum hanno **nomi identici**: aggiungere o rinominare
  una costante in un enum richiede di farlo anche nell'altro.
- Il mapper resta null-safe: se l'enum sorgente è null il risultato è null e
  `valueOf` non viene invocato (la presenza si controlla nel validator).
- Una costante senza corrispondenza produce `IllegalArgumentException`: è un
  errore di programmazione e non va nascosto né gestito. Ogni mapper con enum
  ha un test unitario che verifica la corrispondenza di tutte le costanti.
- La regola riguarda solo le conversioni enum -> enum; le altre conversioni
  (es. enum -> `String` di una response) non cambiano.

## Iniezione delle dipendenze

- Le dipendenze dei bean Spring (`RestResource`, service, validator, adapter,
  mapper, configurazioni) si iniettano con `@Autowired` sul campo
  (field injection); non si usa l'iniezione tramite costruttore.
- Restano fuori dalla regola i parametri dei metodi `@Bean`.
- I test unitari istanziano la classe e valorizzano i campi con Mockito
  (`@Mock` / `@InjectMocks`).

## Validazione per scenario

- **Ogni scenario/operazione REST ha il proprio validator dedicato**, in
  `application/validation` della feature.
- La validazione **raccoglie tutti gli errori** riscontrati (non fail-fast):
  il validator esegue tutti i controlli previsti, non si ferma al primo
  fallimento.
- Componenti nel core (`com.capgemini.smart.simulator.core.validation`):
  - `ValidationError` — record con `field` e `message`
  - `ValidationResult` — lista di `ValidationError`, `isValid()`, metodo
    builder-style `insertError(...)` per accumulare errori
  - contratto generico per i validator di scenario (metodo `validate(...)`
    che restituisce `ValidationResult`)
- Flusso nel service applicativo: chiama il validator come primo passo →
  se `isValid()` è `false`, lancia `ValidationException`
  (`com.capgemini.smart.simulator.core.exception.ValidationException`,
  incapsula il `ValidationResult`, esposto anche via `getErrors()`) →
  altrimenti procede con la business logic.

## Gestione errori — tre percorsi

1. **Validazione formale/di input** (presenza campi, valori ammessi) →
   `ValidationResult`/`ValidationException` del core →
   `GlobalExceptionHandler` → `ApiError` (record con campo
   `errors: List<ValidationError>`, annotato
   `@JsonInclude(JsonInclude.Include.NON_NULL)` — il campo è omesso dal
   JSON quando non è un errore di validazione, invece di comparire come
   `"errors": null`). **HTTP 400**, lista completa degli errori.
   **Valore non convertibile al tipo del campo DTO** (enum non valido,
   valore non numerico per un campo numerico, ecc.): la deserializzazione
   Jackson fallisce prima del validator (`HttpMessageNotReadableException`
   con causa `InvalidFormatException`); `GlobalExceptionHandler` (core) la
   converte in HTTP 400 con `ApiError` e un `ValidationError` con il
   percorso del campo (es. `scenarioCode`, `listDocuments[2]`) e un
   messaggio: per un enum elenca i valori ammessi, per gli altri tipi
   indica che il valore non è valido per il tipo atteso, nel formato
   `Valore non valido: '<valore>'. Tipo atteso: <NomeSemplice>` (per un
   enum: `Valore non ammesso: '<valore>'. Valori ammessi: ...`). In questo caso la
   risposta contiene **solo** questo errore (non aggregato con gli errori
   del validator): comportamento accettato per tutti i campi del progetto.
   **Corpo non leggibile** (JSON malformato, body assente, tipo sbagliato
   per un'intera struttura come una stringa al posto di una lista, ogni
   altra causa di `HttpMessageNotReadableException` diversa da
   `InvalidFormatException`): `GlobalExceptionHandler` risponde HTTP 400
   con `ApiError` e un solo `ValidationError`; `field` = percorso del campo
   se Jackson lo conosce, altrimenti `null`; `message` generico
   "Corpo della richiesta non valido o malformato" (non si espone il
   messaggio di Jackson); log a livello `warn`. Le altre eccezioni non
   gestite restano HTTP 500 (`handleGeneric`).
   In entrambi i rami (valore non convertibile e corpo non leggibile) il
   campo `message` di primo livello di `ApiError` è un testo fisso, nello
   stile di `ValidationException` (`Richiesta non valida: <N> errore/i`),
   mai il messaggio di Jackson; il messaggio originale va solo nel log.
2. **Violazioni di invarianti di dominio** → `DomainValidationException`
   locale alla feature (puro Java, nessuna dipendenza da Spring/core),
   intercettata da un `@ControllerAdvice` **scoped** al RestResource, mappata
   alla stessa forma di `ApiError` (un singolo `ValidationError` con
   `field: null`).
3. **Scenari dichiarati REPLICA ORIGINALE** (vedi sotto) — gestione errori
   completamente custom, non necessariamente `ApiError`.

## Eccezione: scenari che replicano un comportamento originale

Alcuni servizi REST devono replicare **fedelmente** interfaccia e
comportamento (status code, formato errori, struttura request/response,
naming campi — **ogni aspetto del contratto**) di un sistema esterno reale
che stanno simulando, invece di seguire lo standard sopra descritto.
Questa è un'eccezione **dichiarata**, mai una scelta implicita.

**Come indicarla nelle specifiche** (quando l'utente le fornisce a Claude):

```
Comportamento: REPLICA ORIGINALE (sistema: <NomeSistema>, rif: <WSDL/doc>)
```

**Come si traduce nel codice:**
- Annotazione `com.capgemini.smart.simulator.core.annotation.ReplicatesOriginalBehavior`
  (attributi: `system` obbligatorio, `reference` opzionale), applicata sul
  RestResource o sul singolo metodo che deroga
- Il contratto REST segue il sistema originale, **non** le convenzioni
  standard — tipicamente tramite uno `@RestControllerAdvice` scoped al
  singolo RestResource, con priorità sul `GlobalExceptionHandler`
- Anche la validazione può seguire una logica diversa (es. fail-fast) se è
  il comportamento del sistema originale
- La business logic interna e i concetti di dominio restano comunque
  quelli standard del progetto: è solo il **contratto esterno** a poter
  cambiare

### Moduli con interfacce autogenerate da YAML

Quando il contratto di un intero modulo è definito da YAML OpenAPI (caso del
raccordo partitario) la deroga REPLICA ORIGINALE vale per tutte le `RestResource`
del modulo, con queste regole:

- **Generazione**: `openapi-generator-maven-plugin` (versione gestita nel
  `pluginManagement` del parent), generatore `spring` con `interfaceOnly`,
  `useSpringBoot3`, Jakarta EE, `skipDefaultInterface`, `useBeanValidation`,
  `useTags`; nessun supporting file, test o documentazione generati. Una
  execution per ogni YAML con `paths`; il file dei componenti comuni non ha
  execution. Il codice generato sta in `target/generated-sources` e non si
  committa.
- **Contratto**: nomi di campi, enum, modelli, path e status code sono quelli
  dello YAML, senza adattamenti (niente suffisso `Code`, niente `DtoReq`/
  `DtoResp`, niente enum gemelli). Gli YAML non si modificano mai.
- **Template `pojo.mustache`** del modulo (copia del template del generatore
  alla versione in uso, con due `@JsonIgnore` in più sui metodi fluenti), per
  evitare che Jackson legga i metodi fluenti `set*` come setter e pubblichi
  proprietà non presenti nello YAML. Da riconfrontare con l'originale e da
  riscansionare a ogni upgrade del plugin.
- **RestResource**: implementa l'interfaccia generata, annotata
  `@ReplicatesOriginalBehavior(system, reference)` con `reference` = nome del
  file YAML. Per i servizi non implementati ogni metodo lancia
  `NotImplementedOperationException`.
- **Errori**: un `@RestControllerAdvice` scoped (`assignableTypes`) produce la
  busta dello YAML per: bean validation, `ConstraintViolationException`,
  deserializzazione, corpo non leggibile e 501. `errorCode` = status come
  stringa; testi fissi in inglese (quelli presenti nello YAML hanno la
  precedenza); `technicalDescription` = percorso del campo + messaggio;
  `errorList` con tutte le violazioni ordinate per percorso del campo
  (alfabetico, a parità per messaggio, quelle senza percorso per prime) e
  `mainError` uguale alla prima; `header` riecheggiato dalla richiesta
  (senza campi null) o, se non disponibile, generato con `timestamp` e
  `trackId`.
- **Header di risposta di successo**: la response di successo riecheggia l'`header`
  della request senza modifiche (stesso oggetto e valori, nessun attributo
  generato, nessuna copia via Jackson). Si applica solo alle response il cui
  contratto YAML dichiara `header`; se la response non lo dichiara non si
  aggiunge nulla (né attributi né header HTTP). La regola degli errori non
  cambia.
- **Lingua**: un interceptor del modulo imposta `Locale.ENGLISH` solo quando il
  handler è una `RestResource` del modulo (confronto sul tipo, non sul path), così
  i messaggi di bean validation sono in inglese senza toccare gli altri
  sottosistemi.
- **Confini**: `domain` e `application` seguono lo standard e non importano
  classi generate né web. Si creano solo all'implementazione di un servizio,
  con l'esagono diviso per operazione; il mapper converte tra modelli generati
  e dominio; gli enum generati si convertono con `valueOf` sul nome della
  costante (vedi "Mapping degli enum").
- **Verifiche tipiche** dopo ogni intervento sul modulo: build verde, tutti gli
  endpoint non implementati rispondono 501, `/v3/api-docs` coincide con gli
  YAML, sha256 degli YAML invariati.

## Struttura dei moduli e avvio

- **Un solo modulo avviabile**: `smart-simulator-app`. Contiene l'unica
  classe `@SpringBootApplication` (package radice
  `com.capgemini.smart.simulator`, così la scansione vede tutti i
  moduli), l'unico `spring-boot-maven-plugin` e l'unico `application.yml`
  generale (più i file per ambiente `application-<env>.yml`, vedi
  "Configurazione per ambiente"). Dipende da `smart-simulator-core` e da tutti i moduli
  sottosistema. Un solo processo espone i servizi di tutti i sottosistemi,
  con un'unica Swagger UI su `/swagger-ui.html`.
- `smart-simulator-core` e ogni modulo sottosistema sono **solo
  librerie**: nessuna classe main, nessun `spring-boot-maven-plugin`,
  nessun `application.yml`.
- Un nuovo sottosistema nasce come libreria (stessa struttura di
  `smart-simulator-paperless`) e viene aggiunto come dipendenza di
  `smart-simulator-app`.

## Gestione configurazione multi-modulo

- Il **core** contiene SOLO lo **schema** di configurazione (classi
  `@ConfigurationProperties`, es. mappe tipo `brokerConfigMap`/`queueMap`
  per IBM MQ, analoga `topicMap` per Kafka) — **mai i valori**.
- Con un solo boot esiste **un solo `application.yml` sul classpath**,
  quindi i moduli sottosistema **non hanno un `application.yml`**: ognuno
  ha un **file di configurazione dedicato con nome univoco** (es.
  `paperless-config.yml`, in `src/main/resources` del modulo) con i propri
  valori di broker/coda/topic e di `smart-simulator.defaults.<sottosistema>`.
  Il file è importato dall'`application.yml` di `smart-simulator-app`
  via `spring.config.import`. Nessuna configurazione duplicata nel core
  sul presupposto che possa servire in futuro ad altri moduli.
- I valori realmente condivisi da ogni sottosistema (porta, datasource,
  connessione IBM MQ, bootstrap Kafka) stanno nell'app, mai nel core:
  quelli uguali in tutti gli ambienti nell'`application.yml`, quelli che
  dipendono dall'ambiente negli `application-<env>.yml` (vedi
  "Configurazione per ambiente").
- Le chiavi di `queueMap`/`topicMap`/`brokerConfigMap` devono essere uniche
  tra i sottosistemi: se due file definiscono la stessa chiave con valori
  diversi, una sovrascrive l'altra.
- **Un producer/port/adapter per funzionalità**: anche se più scenari
  scrivono sulla stessa coda/topic fisico, ciascuno ha la propria chiave
  `queueMap`/`topicMap` dedicata, la propria `port/out`, il proprio
  adapter — mai condivisi genericamente.
- Se un valore che era gestito via `DefaultsResolver` diventa un **campo
  obbligatorio** della request, il meccanismo `DefaultsResolver` per
  quel campo specifico va rimosso (non serve più un fallback di default).

## Configurazione per ambiente

Il software usa a runtime i file di configurazione dell'ambiente in cui
gira. L'ambiente si seleziona con il **profilo Spring**; lo stesso jar gira
in ogni ambiente senza ricompilare.

- **Ambienti e profili ammessi**: `dev` e `sit`. L'ambiente `dev` coincide
  con lo stack locale di `docker-compose.yml` (PostgreSQL su `localhost:5433`,
  Kafka su `localhost:9092`, IBM MQ `QM1`). Non esiste un profilo `local`.
  Un nuovo ambiente si aggiunge solo con una decisione esplicita, che
  aggiorna l'elenco dei profili ammessi e crea i file di ambiente di app e
  sottosistemi.
- **Attivazione**: solo dall'esterno (`SPRING_PROFILES_ACTIVE`,
  `--spring.profiles.active`, `env` del `launch.json` in VSCode). Nei file
  nel jar non compaiono mai `spring.profiles.active` né
  `spring.profiles.default`: non esiste un profilo di default.
- **Fail-fast sul profilo**: all'avvio deve essere attivo **esattamente
  uno** tra i profili ammessi. Se manca, se non è tra quelli ammessi o se ne
  sono attivi più di uno, l'applicazione non parte e il messaggio d'errore
  elenca i profili ammessi. Il controllo sta nel modulo `smart-simulator-app`
  (mai nel core, che contiene solo lo schema di configurazione) e interviene
  prima della creazione dei bean (`EnvironmentPostProcessor`).
- **File dell'app** (`smart-simulator-app/src/main/resources`):
  `application.yml` contiene solo ciò che è identico in tutti gli ambienti
  più `spring.config.import`; `application-<env>.yml` contiene i valori che
  dipendono dall'ambiente (porta, datasource, connessione IBM MQ, bootstrap
  Kafka, livelli di log, Swagger).
- **File dei sottosistemi** (`<sottosistema>-config.yml` e
  `<sottosistema>-config-<env>.yml`, in `src/main/resources` del modulo, file
  separati e non documenti multipli): il file base contiene ciò che è
  identico in ogni ambiente (es. `smart-simulator.defaults.<sottosistema>`);
  il file di ambiente contiene i valori che dipendono dall'ambiente, cioè
  i blocchi di mappe broker/code/topic che esistono nel sottosistema (oggi,
  in paperless, solo `queue-map`, con chiavi in kebab-case; `brokerConfigMap`
  e `topicMap` solo quando il codice li introduce), interi. Un valore che dipende dall'ambiente non resta mai nel file base.
- **Chiavi**: le chiavi di `queueMap`/`topicMap`/`brokerConfigMap` restano
  uniche tra i sottosistemi e identiche in tutti gli ambienti; cambiano solo
  i valori.
- **Credenziali**: nei file di ambiente, in chiaro, insieme a utenti, host e
  porte. Non si usano placeholder né variabili d'ambiente obbligatorie. Il
  meccanismo nativo di Spring Boot per sovrascrivere un singolo valore
  dall'esterno (variabile d'ambiente, `spring.config.additional-location`)
  resta disponibile, senza codice dedicato e senza renderlo un requisito.
- **Nuovo sottosistema**: oltre al file base, ha un file di ambiente per
  ciascun profilo ammesso, caricato come descritto sopra.
- **Test**: i test che avviano il contesto Spring attivano esplicitamente
  un profilo ammesso (`@ActiveProfiles("dev")`), perché il fail-fast vale
  anche per loro.

## Persistenza

- **Nessun Hibernate/JPA**: l'accesso al database avviene con **Spring JDBC
  (`JdbcTemplate`)** e SQL scritto a mano. Niente entity, repository JPA,
  `spring.jpa.*`, `default_schema` globale.
- Il codice di persistenza vive solo in `adapter/out/persistence` della
  feature e implementa una `port/out`; `domain` e `application` non vedono
  mai tipi JDBC o righe di tabella (il mapping riga <-> modello di dominio
  è nell'adapter).
- Lo **schema** del sottosistema è scritto **esplicitamente nell'SQL** di
  ogni adapter (es. `paperless.<tabella>`): nessuna query cross-schema, nessun
  default globale che possa far finire per errore una tabella nello schema
  sbagliato.
- Un solo `DataSource` condiviso (configurato negli `application-<env>.yml` di
  `smart-simulator-app`). Schema e tabelle restano creati da script SQL
  gestiti a parte (niente Flyway/Liquibase, niente generazione schema).
- La dipendenza `spring-boot-starter-jdbc` sta nel core e ogni sottosistema
  la eredita, come per springdoc.

## Trasporto MQ: JSON vs testo grezzo

`MqPublisherService` (core) supporta due modalità:
- **JSON** (default, via converter Jackson/`JmsConfig`) — per payload
  generici
- **Testo grezzo** (metodo dedicato, bypassa il converter JSON) — per i
  casi in cui il formato sul wire deve essere un formato specifico non-JSON
  (es. XML costruito via JAXB), per restare fedeli al contratto atteso dal
  sistema reale simulato

Questa è una capacità **trasversale** (non specifica di un sottosistema),
quindi vive nel core, coerente con la regola sul codice condiviso sotto.

## Codice comune e riutilizzabile

Due livelli:

1. **Trasversale a tutto il progetto** → core, package
   `com.capgemini.smart.simulator.core.util`:
   - `StringUtil` — `isBlank`, `isNotBlank`
   - `DateTimeUtil` — `formatInstant`/`parseInstant`,
     `formatLocalDateTime`/`parseLocalDateTime` (ISO-8601). **Scelta
     intenzionale**: un input non convertibile lancia
     `DateTimeParseException` (comportamento bloccante), a differenza di
     `NumberUtil`/`JsonUtil` — una data non valida è considerata un
     errore, non un caso d'uso normale da gestire "con grazia"
   - `CollectionUtil` — `isEmpty`, `isNotEmpty`, `hasElements` (alias
     semantico di `isNotEmpty`, mantenuto per leggibilità al chiamante)
   - `NumberUtil` — `isNullOrZero`, `parseInteger`/`parseLong`/
     `parseBigDecimal` (parsing sicuro, **`Optional`**, nessuna eccezione)
   - `JsonUtil` — (de)serializzazione sicura via Jackson, **`Optional`**,
     log di warning in caso di errore di parsing
   - `RandomUtil` — `generateNumericString(int length)`: stringa di cifre
     numeriche random della lunghezza richiesta (usata per `IdPratica`,
     `DM_IdProcesso` e simili); sostituisce i generatori locali alle feature
   - capacità trasversali infrastrutturali (es. invio testo grezzo via MQ,
     vedi sopra) vanno nella classe del core pertinente (es.
     `MqPublisherService`), non in `util/`, se sono specifiche di un
     componente esistente piuttosto che una utility generica

2. **Trasversale a più funzioni dello stesso modulo sottosistema** (ma
   specifico di quel sottosistema) → resta nel modulo stesso, non sale
   al core.

## Swagger / OpenAPI

Dipendenza `org.springdoc:springdoc-openapi-starter-webmvc-ui`, gestita
in `dependencyManagement` nel pom parent e inclusa come dipendenza diretta
nel pom del **core** — ogni modulo sottosistema la eredita automaticamente
senza configurazione aggiuntiva. UI disponibile su `/swagger-ui.html` di
ciascun modulo avviato (oggi un'unica UI, quella dell'app). Gli endpoint
sono raggruppati per area tramite `@Tag` sul `RestResource`.

## Nota di processo per Claude Code

Ogni prompt di implementazione destinato a Claude Code deve includere
esplicitamente:
- l'istruzione di **non prendere decisioni autonome**, applicare solo le
  specifiche indicate
- l'istruzione di **fermarsi e chiedere** in caso di ambiguità o casi non
  coperti, invece di scegliere in autonomia (e, se in auto-mode, di
  segnalare esplicitamente ogni scelta presa per poterla rivedere)
- il richiamo alle convenzioni di naming/architettura/validazione
  pertinenti al task specifico

Quando Claude Code segnala una scelta presa per ambiguità, o pone una
domanda tecnica con alternative, Claude valuta le opzioni **solo sulla
base di quanto già specificato/concordato** (non introduce considerazioni
su sviluppi futuri non ancora richiesti), e propone la propria
raccomandazione prima di passare la decisione finale all'utente.
