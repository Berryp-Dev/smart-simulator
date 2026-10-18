# SmartSimulator — Contesto di progetto

Documento di contesto per riprendere il lavoro su questo progetto in una
nuova chat. Da leggere insieme a `SmartSimulator-CODING-STANDARDS.md`
(regole di codice/architettura) e `SmartSimulator-SERVICES-SPECIFICATION.md`
(specifiche di dettaglio di ogni servizio REST implementato).

## Cos'è il progetto

Piattaforma per la simulazione di sistemi esterni (REST e/o SOAP), che
replica funzionalità di diversi sistemi reali (es. Paperless...). Ogni operazione simulata, dove previsto, pubblica
eventi su Kafka e/o invia messaggi su IBM MQ.

- **groupId Maven**: `com.capgemini.smart.simulator` (company: `com.capgemini`)
- **Package radice**: `com.capgemini.smart.simulator` (coincide con il groupId)
- **Frontend futuro**: microfrontend con componenti Angular 21 (non ancora
  affrontato in questa fase, solo vincolo noto)
- **Visione di progetto**: SmartSimulator è il prototipo di riferimento
  per standardizzare i futuri progetti a microservizi con architettura
  esagonale. Ogni decisione di naming/organizzazione/pattern va presa
  pensando alla replicabilità come default futuro, non solo alla
  correttezza locale.

## Regole di processo (workflow di lavoro)

Regola fondamentale, valida per tutta la durata del progetto:

1. L'utente (Berry) fornisce le specifiche
2. Claude propone una struttura/design — **senza scrivere codice**
3. L'utente conferma o richiede revisioni
4. Solo dopo conferma esplicita, Claude produce il **prompt per Claude Code**
   (mai il codice direttamente), con l'istruzione esplicita che Claude Code
   deve applicare fedelmente le specifiche indicate, senza prendere
   decisioni autonome, e fermarsi a chiedere in caso di dubbio o ambiguità
5. Claude non crea/modifica file né esegue comandi sul progetto senza
   conferma esplicita dell'utente

Quando Claude Code segnala scelte fatte per ambiguità non coperte dal
prompt (es. in modalità auto-mode), l'utente le rivede una per una con
Claude, che aggiorna gli standard di progetto di conseguenza e prepara un
messaggio di correzione/conferma da ridare a Claude Code.

## Struttura del repository

Progetto Maven multi-modulo:

```
smart-simulator/
├── pom.xml                        (parent, packaging pom, elenco moduli,
│                                    dependencyManagement incl. springdoc)
├── docker-compose.yml             (Postgres, Kafka, IBM MQ per test locale)
├── README.md
├── ARCHITECTURE.md                 (convenzioni per i moduli sottosistema)
├── smart-simulator-app/          (unico modulo avviabile, vedi sotto)
│   ├── pom.xml                      (dipende da core e da tutti i sottosistemi;
│   │                                unico spring-boot-maven-plugin)
│   └── src/main/
│       ├── java/.../SmartSimulatorApplication.java  (unico @SpringBootApplication)
│       └── resources/application.yml  (porta, datasource, MQ, Kafka;
│                                      spring.config.import dei file dei sottosistemi)
├── smart-simulator-core/         (libreria condivisa, non avviabile)
│   ├── pom.xml
│   └── src/main/java/com/capgemini/smart/simulator/core/
│       ├── kafka/       KafkaPublisherService
│       ├── mq/          MqPublisherService (+ metodo per testo grezzo/XML), JmsConfig
│       ├── config/      DefaultsResolver, SubsystemDefaultsProperties, StaticDefaultsResolver
│       ├── rest/        ApiError, GlobalExceptionHandler (incl. valori non
│       │                convertibili al tipo del campo DTO, es. enum non valido)
│       ├── soap/        CxfCommonConfig
│       ├── validation/  ValidationError, ValidationResult (+ contratto validator di scenario)
│       ├── exception/   ValidationException
│       ├── annotation/  ReplicatesOriginalBehavior
│       └── util/        StringUtil, DateTimeUtil, CollectionUtil, NumberUtil, JsonUtil, RandomUtil
├── smart-simulator-paperless/    (primo modulo sottosistema, libreria)
│   ├── pom.xml                      (dipende da smart-simulator-core)
│   ├── paperless-config.yml         (broker/coda MQ del sottosistema, importato
│   │                                 dall'app)
│   └── src/main/java/com/capgemini/smart/simulator/paperless/
│       ├── config/                  (wiring a livello di modulo)
│       ├── practice/                (area)
│       │   └── configure/           (API, struttura esagonale completa)
│       └── document/                (area)
│           └── signature/           (API, struttura esagonale completa)
├── smart-simulator-document-viewer/  (modulo sottosistema, libreria)
│   ├── pom.xml                      (dipende da smart-simulator-core; surefire 3.2.5)
│   └── src/main/
│       ├── java/com/capgemini/smart/simulator/documentviewer/
│       │   ├── config/              (DocumentViewerProperties + wiring)
│       │   └── staticdoc/           (API a due livelli, senza area; esagono completo)
│       └── resources/document-viewer-config.yml  (products-folder: pdf/static)
│           pdf/static/                           (i PDF, <productCode>.pdf)
└── smart-simulator-account-raccordo-partitario/  (secondo modulo sottosistema, libreria)
    ├── pom.xml                      (una execution di openapi-generator per YAML con `paths`)
    └── src/main/
        ├── java/com/capgemini/smart/simulator/account/raccordopartitario/
        │   ├── common/adapter/in/rest/   (advice, busta d'errore, eccezione)
        │   ├── config/                   (interceptor locale, web config)
        │   ├── sales/adapter/in/rest/resource/
        │   └── currentaccount/configure/adapter/in/rest/resource/
        └── openapi-templates/pojo.mustache
    (gli YAML stanno in smart-simulator-app/src/main/resources/openapi/account-raccordo-partitario/)
```

**Struttura (ristrutturazione eseguita e verificata)**:
un solo modulo avviabile, `smart-simulator-app`; `smart-simulator-core`
e `smart-simulator-paperless` diventano solo librerie (niente main, niente
`spring-boot-maven-plugin`, niente `application.yml`). Un solo processo
espone tutti i servizi di tutti i sottosistemi, con un'unica Swagger UI.
Regole in CODING-STANDARDS.md, sezione "Struttura dei moduli e avvio".

Il Boot Dashboard di VSCode mostra una sola app (`.vscode/launch.json`
aggiornato). L'app si avvia con `smart-simulator-app` (fat-jar tramite
`repackage`, configurato solo nel pom dell'app) e un solo processo espone
i servizi di tutti i sottosistemi.

## Decisioni infrastrutturali consolidate

- **Spring Boot** 3.3.x, **Java 21**, nessuna security
- **Database**: PostgreSQL, nome DB `smart_simulator`
  - uno schema per sottosistema simulato (es. `paperless`)
  - più uno schema tecnico trasversale `smart_simulator` (audit/config/correlazione)
  - **nessuna query cross-schema**: l'accesso tra sottosistemi avviene solo
    tramite i servizi REST/SOAP esposti
  - schema e tabelle creati tramite **script SQL gestiti a parte** in fase
    di provisioning (niente Flyway/Liquibase)
  - accesso con **Spring JDBC (`JdbcTemplate`)**, nessun Hibernate/JPA
    (JPA rimosso: `spring-boot-starter-jdbc` nel core, nessun `spring.jpa.*`)
- **REST e SOAP possono coesistere** sullo stesso sottosistema (mix libero
  per modulo)
- **SOAP**: Apache CXF, approccio **contract-first** dai WSDL reali
  (stub generati via `cxf-codegen-plugin` nel pom del singolo modulo,
  quando il WSDL sarà disponibile — i WSDL vengono caricati al bisogno)
- **Kafka**: producer generico (`KafkaPublisherService` nel core, basato su
  `KafkaTemplate`)
- **IBM MQ**: producer generico (`MqPublisherService` nel core, basato su
  `JmsTemplate` + `mq-jms-spring-boot-starter`). Due modalità: payload
  serializzato in JSON (default) e un metodo dedicato per inviare **testo
  grezzo** (es. XML) senza passare dal converter Jackson, aggiunto per i
  casi in cui il formato sul wire deve essere XML puro (vedi standard di
  gestione configurazione sotto)
- **Swagger/OpenAPI**: dipendenza `springdoc-openapi-starter-webmvc-ui`
  nel core (ereditata automaticamente da ogni modulo sottosistema), UI
  disponibile su `/swagger-ui.html` di ciascun modulo avviato
- **Interfacce autogenerate da YAML** (raccordo partitario): contratto generato
  con `openapi-generator-maven-plugin` 7.19.0 (property
  `jackson-databind-nullable` 0.2.12 nel parent), `interfaceOnly`; gli YAML
  vivono in `smart-simulator-app/src/main/resources/openapi/<raccordo>/` e non si
  modificano. Vedi CODING-STANDARDS, "Moduli con interfacce autogenerate da YAML"
- **Request DTO per operazione**: ogni scenario/operazione ha la propria
  request dedicata; i valori non specificati esplicitamente E non
  obbligatori vengono risolti tramite `DefaultsResolver` (impl. base:
  lettura da `application.yml`, sezione
  `smart-simulator.defaults.<sottosistema>`) — se un valore diventa un
  campo obbligatorio della request, il meccanismo DefaultsResolver per
  quel campo va rimosso (non serve più un fallback)

- **Configurazione per ambiente**: profili Spring `dev` e `sit` (`dev` =
  docker-compose locale; nessun profilo `local`), attivati solo dall'esterno;
  nessun profilo di default, senza un profilo ammesso l'app non parte.
  File `application-<env>.yml` nell'app e `<sottosistema>-config-<env>.yml`
  nei sottosistemi (file separati); credenziali in chiaro nei file di
  ambiente, nessun placeholder obbligatorio. Regole in CODING-STANDARDS.md,
  sezione "Configurazione per ambiente".

## Ambiente locale (docker-compose.yml)

Servizi disponibili per test locale (`docker compose up -d`):

- Container `smart-simulator-postgres`, `smart-simulator-kafka`, `smart-simulator-ibmmq`; volume Postgres `smart-simulator-pgdata`
- **Postgres 16** — DB/utente/password `smart_simulator`
  - porta host **5433** (non 5432: rimappata per conflitto con un Postgres
    locale già presente sulla macchina di sviluppo)
- **Kafka** (`apache/kafka:3.7.0`, KRaft, single-node) — porta 9092
- **IBM MQ** (developer edition) — queue manager `QM1`, canale
  `DEV.APP.SVRCONN`, utente applicativo `app` / password `passw0rd`,
  utente admin `admin` / password `passw0rd` (**`MQ_ADMIN_PASSWORD` deve
  essere impostata esplicitamente nel compose**, altrimenti il login alla
  console web fallisce sempre — non c'è default), porta 1414
  (+9443 console web, `https://localhost:9443/ibmmq/console`)

Valori dell'ambiente `dev` (dopo la configurazione per ambiente, in
`application-dev.yml` di `smart-simulator-app`; prima nell'`application.yml`):
- `spring.datasource.url` → `jdbc:postgresql://localhost:5433/smart_simulator`
- `ibm.mq.password` default → `passw0rd`
- porta: l'app unica gira sulla **9082** (8080 era occupata sulla macchina di
  sviluppo; la 9081 del vecchio core non esiste più)

**Tool VSCode utili**:
- estensione **"IBM MQ Console"** (IBM, non sul Marketplace — VSIX scaricato
  manualmente dal repo `ibm-messaging/mq-console-vscode-extension`): apre
  la console web di IBM MQ nel browser integrato di VSCode (comando
  "IBM MQ Console: Open Console")
- **Swagger UI** dell'app in esecuzione, per testare gli endpoint REST
  (`http://localhost:<porta>/swagger-ui.html`; oggi la porta del modulo
  paperless, 9082)

## Stato di avanzamento

**Document Viewer + context-path** (17/10/2026):
- L'app ha il context-path `/smart-simulator` (`server.servlet.context-path`
  in `application.yml`): i path degli endpoint nei documenti sono relativi a
  questo prefisso; Swagger UI su `/smart-simulator/swagger-ui.html`.
- Nuovo modulo libreria `smart-simulator-document-viewer`, servizio
  **Static Doc (retrieve)**: `GET /smart-simulator/document-viewer/staticdoc?productCode=...`,
  restituisce il PDF letto dal classpath del modulo (inline): in tutti gli
  ambienti i PDF stanno in `src/main/resources/pdf/static/` e finiscono nel
  jar, per aggiungerne uno si rifà il build. **Implementato** e coperto da
  test unitari; smoke test (200/400/404, Paperless invariato, Swagger)
  eseguito da jar.
- **Da verificare a mano**: mettere un PDF `12234.pdf` in
  `smart-simulator-document-viewer/src/main/resources/pdf/static/`, rifare il
  build o riavviare da IDE (Boot Dashboard, profilo `dev`) e aprire
  `http://localhost:9082/smart-simulator/document-viewer/staticdoc?productCode=12234`
  (il PDF deve aprirsi inline nel browser); `productCode` con maiuscole
  diverse → 404; inesistente → 404 con `ApiError`. Specifica in
  `SmartSimulator-SERVICES-SPECIFICATION.md`.

**Fatto:**
- Skeleton multi-modulo Maven, modulo `smart-simulator-core` avviabile e
  funzionante in locale (verificato in VSCode con Spring Boot Dashboard;
  da superare con la ristrutturazione a boot unico)
- Framework di validazione, gestione errori REST centralizzata, utility
  comuni, meccanismo `@ReplicatesOriginalBehavior`, `ARCHITECTURE.md` con
  la struttura esagonale di riferimento (vedi CODING-STANDARDS.md per il
  dettaglio completo, incluse le regole emerse durante l'implementazione
  del primo servizio)
- Modulo `smart-simulator-paperless` creato, avviabile (porta 9082),
  Swagger UI configurata e funzionante
- Primo servizio implementato: **Paperless Practice Configure**
  (`POST /paperless/practice/configure`) — vedi
  `SmartSimulator-SERVICES-SPECIFICATION.md` per la specifica completa
  e aggiornata

- Specifica del secondo servizio Paperless **Signature**
  (`POST /paperless/document/signature`) definita — vedi
  `SmartSimulator-SERVICES-SPECIFICATION.md`
- Standard aggiornati: ogni campo enum è enum anche nel DTO (deroga `String`
  rimossa), valori non convertibili gestiti dall'handler del core,
  `RandomUtil` nel core — vedi CODING-STANDARDS.md

**Completati e verificati:**
- `PROMPT-ClaudeCode-Paperless-Signature.md` (Task A, B, C) e
  `PROMPT-ClaudeCode-Fix-Handler-Body-Malformato.md`: test da Swagger OK
- `PROMPT-ClaudeCode-Ristrutturazione-Boot-Unico.md`: build verde, app unica
  sulla 9082, messaggi verificati su `DEV.QUEUE.2` (Configure) e
  `DEV.QUEUE.1` (Signature) via `runmqsc`. Il `spring-boot-maven-plugin` ora
  ha versione e `repackage` espliciti nel pom dell'app (prima non
  produceva jar eseguibili)

**Rinomina progetto ServicesSimulator → SmartSimulator: completata (2026-10-06).**
Applicate le quattro forme del nome (vedi CODING-STANDARDS.md, "Convenzioni di
naming"): moduli/jar/container/volume/prefissi `smart-simulator`, package e
groupId `com.capgemini.smart.simulator`, database/utente/schema tecnico
`smart_simulator`, classi e documenti `SmartSimulator`. Build verde, binding dei
prefissi verificato, avvio su 9082 e Swagger OK. Da fare a mano: ricreare
l'ambiente Docker e il database `smart_simulator`, ripetere la baseline di
Swagger e dei messaggi MQ. I prompt storici in `docs/prompts/` mantengono i vecchi
nomi (cronologia).

**Configurazione per ambiente, ambiente `dev`: eseguita.**
`PROMPT-ClaudeCode-Configurazione-Ambienti.md`: build verde, valori effettivi di
`dev` identici alla baseline (17 proprietà), fail-fast verificato sul jar
(senza profilo, con `prod`, con `dev,sit`). Creati `application-dev.yml`
(app), `paperless-config-dev.yml` (solo `queue-map`, chiavi kebab-case
invariate), `ActiveProfileValidator` (`EnvironmentPostProcessor` nel package
`com.capgemini.smart.simulator.config` del modulo app, registrato in
`META-INF/spring.factories`) e `SPRING_PROFILES_ACTIVE=dev` in `launch.json`.
I valori di `dev` sono in chiaro: i vecchi default dei placeholder
(`${VAR:default}`) sono stati sostituiti dai valori effettivi, quindi le
variabili `DB_*`, `MQ_*`, `KAFKA_BOOTSTRAP_SERVERS` non sono più lette.
Da verificare a mano: avvio da VSCode (Boot Dashboard) e messaggi MQ con
il profilo `dev`.

**Modulo `smart-simulator-account-raccordo-partitario`: creato (2026-10-07).**
Interfacce REST autogenerate da tre YAML (`components-v1.2.2.yaml`,
`raccordopartitario_sales.yaml`, `raccordopartitario_sales_currentaccounts_configure.yaml`)
con `openapi-generator-maven-plugin` 7.19.0; 12 operazioni, **tutte non
implementate** (HTTP 501 con la busta dello YAML). Deroga REPLICA ORIGINALE
(sistema: Raccordo Partitario Conti) confinata in `adapter/in/*`; `domain` e
`application` esistono solo per i servizi implementati (oggi `create`). Package radice
`com.capgemini.smart.simulator.account.raccordopartitario`; il livello
`lifecycle` inizialmente previsto per Sales è stato rimosso. Corretto con un
template `pojo.mustache` il problema dei metodi fluenti `set*` letti da Jackson
(proprietà `tlement...` inventate). Errori in inglese, violazioni ordinate per
percorso del campo (`mainError` deterministico), inglese imposto solo ai
messaggi del modulo da un interceptor sul tipo del handler. Verificati: build
verde, Swagger 200, 12 endpoint a 501, `/v3/api-docs` identico agli YAML,
sha256 degli YAML invariati, Paperless invariato. Dettaglio in
`SmartSimulator-SERVICES-SPECIFICATION.md`, procedura per un nuovo raccordo in
`README.md`.

**Primo servizio del raccordo implementato: `create` (2026-10-07).** POST
`/smartengine/raccordo/create` → 201 con `srvAcntKey` (codici dalla request, `key`
UUID), `statusInfo` (`CREATED`, `Creato`, `tsStatus` UTC al millisecondo); la
response non ha `header` per contratto. Esagono in `sales/create` (domain,
application, mapper), senza persistenza; primi test unitari del modulo (surefire
3.2.5 fissato nel pom del modulo, il default di Maven non esegue JUnit 5). Gli
altri 11 servizi restano 501. Dettaglio in `SmartSimulator-SERVICES-SPECIFICATION.md`.

**Aggiornamento standard (2026-10-17): documentato, codice da allineare.** Quattro
nuove direttive in CODING-STANDARDS.md: (1) classi REST con suffisso `RestResource`;
(2) mapping degli enum adapter <-> dominio con `valueOf` (non più `switch`); (3) interfacce
dei casi d'uso con suffisso `Service` e implementazioni con suffisso `ServiceImpl`
(il suffisso `UseCase` è abolito); (4) injection con `@Autowired` sul campo. Il codice
esistente va allineato con `PROMPT-ClaudeCode-Allineamento-Direttive.md` (da eseguire).

**In attesa:** ambiente `sit` (profilo già ammesso dal controllo all'avvio,
ma `application-sit.yml` e `paperless-config-sit.yml` non esistono ancora,
mancano i valori).

**In corso:** nessuna esecuzione aperta.

**Sequenza concordata dei prossimi passi:**
1. Eseguire `PROMPT-ClaudeCode-Paperless-Signature.md` (Task A, B, C) sulla
   struttura attuale e verificare, poi
   `PROMPT-ClaudeCode-Fix-Handler-Body-Malformato.md`. **Fatto.**
2. Eseguire `PROMPT-ClaudeCode-Ristrutturazione-Boot-Unico.md` (modulo
   `smart-simulator-app`, core e paperless come librerie). **Fatto.**
3. Rimuovere JPA/Hibernate e adottare JdbcTemplate
   (`PROMPT-ClaudeCode-Rimozione-JPA.md`). **Fatto**: `spring-boot-starter-jdbc`
   nel core, `spring.jpa.*` rimosso, build e avvio OK. La connessione effettiva
   a Postgres sarà verificata con il primo adapter JDBC (il pool Hikari è lazy,
   l'app parte anche con Postgres spento).
4. Solo dopo la verifica dei punti 2 e 3: aggiungere un nuovo sottosistema
   (nasce come libreria, aggiunta come dipendenza dell'app). **Non prima.**

**Refactoring a struttura per aree (regole in CODING-STANDARDS.md, sezione
"Architettura esagonale — Package by Area e Feature"): COMPLETATO**
1. Package spostati in `practice.configure` e `document.signature`, `RestResource`
   rinominati (`PaperlessPracticeConfigureRestResource`,
   `PaperlessDocumentSignatureRestResource`), `@Tag` per area. Fatto.
2. Port in uscita senza JAXB (`PaperlessPracticeStatusUpdate`,
   `PaperlessDocumentStatusUpdate`, enum `PaperlessPracticeStatusCode`),
   conversione solo nell'adapter MQ. Fatto.
3. Il `RestResource` passa allo use case solo oggetti di dominio tramite mapper
   (mapper null-safe). Fatto, risposte HTTP identiche su 9 casi.
4. Modello di dominio di Configure rinominato `PaperlessPracticeConfiguration`
   (nessun suffisso tecnico nei modelli di dominio). Fatto, build verde.

**Da verificare a mano (non fatto da Claude Code):** contenuto dei messaggi
XML su MQ dopo i refactoring 2 e 3 — Configure su `DEV.QUEUE.2` (5 scenari,
`IdPratica` 10 cifre, `DM_IdProcesso` 3 cifre, `MotivoBlocco` solo con
`PRACTICE_LOCKED`), Signature su `DEV.QUEUE.1` (3 scenari, stesso `IdPratica`,
`CodiceFiscale` `XXXXXXXXXXXXXXXX`, un solo `PARZIALE`/`ERRORE_FIRMA`).

**Da fare (prossimo passo):**
- Eseguire `PROMPT-ClaudeCode-Allineamento-Direttive.md` (RestResource, valueOf, Service/ServiceImpl,
  @Autowired) dopo un commit pulito, poi verificare a mano Swagger e messaggi MQ
- Implementare i servizi di `account-raccordo-partitario` uno per volta (primo
  servizio da scegliere): per ciascuno specifica, design di `domain` e
  `application` (esagono diviso per operazione), prompt per Claude Code
- Aperto, da decidere: `target/` risulta tracciato da git nel commit di partenza
  e non esiste un `.gitignore`
- Verificare a mano avvio da VSCode e messaggi MQ con il profilo `dev`;
  poi creare i file di `sit` quando arrivano i valori
- Definire il prossimo sottosistema (nasce come libreria con struttura per
  area/API, aggiunto come dipendenza di `smart-simulator-app`, con
  configurazione in un file `<sottosistema>-config.yml` importato dall'app)
- Il servizio Signature scrive su `DEV.QUEUE.1` (Configure su `DEV.QUEUE.2`),
  con propria port/adapter/chiave `queueMap` dedicata (regola "un producer
  per funzionalità")
