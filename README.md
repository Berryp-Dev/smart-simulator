# SmartSimulator

Piattaforma per la simulazione di sistemi esterni (REST e/o SOAP), con
pubblicazione degli eventi generati su Kafka e invio messaggi su IBM MQ.

Documentazione di riferimento:
- `SmartSimulator-CODING-STANDARDS.md` — standard di codice e architettura
- `SmartSimulator-SERVICES-SPECIFICATION.md` — specifica di ogni servizio REST
- `SmartSimulator-PROJECT-CONTEXT.md` — stato e contesto del progetto
- `ARCHITECTURE.md` — sintesi delle convenzioni per i moduli sottosistema

## Struttura del progetto

```
smart-simulator/
├── pom.xml                        (parent, packaging pom, elenco moduli,
│                                    dependencyManagement incl. springdoc)
├── docker-compose.yml             (Postgres, Kafka, IBM MQ per test locale)
├── smart-simulator-app/         (UNICO modulo avviabile)
│   └── src/main/
│       ├── java/.../SmartSimulatorApplication.java
│       └── resources/application.yml   (valori uguali in ogni ambiente,
│                                        spring.config.import dei sottosistemi)
│           application-dev.yml, application-sit.yml
│                                       (porta, datasource, MQ, Kafka per ambiente)
├── smart-simulator-core/        (libreria condivisa, NON avviabile)
│   └── src/main/java/com/capgemini/smart/simulator/core/
│       ├── kafka/       KafkaPublisherService
│       ├── mq/          MqPublisherService (JSON e testo grezzo), JmsConfig
│       ├── config/      DefaultsResolver, SubsystemDefaultsProperties, StaticDefaultsResolver
│       ├── rest/        ApiError, GlobalExceptionHandler
│       ├── soap/        CxfCommonConfig
│       ├── validation/  ValidationError, ValidationResult, contratto validator
│       ├── exception/   ValidationException
│       ├── annotation/  ReplicatesOriginalBehavior
│       └── util/        StringUtil, DateTimeUtil, CollectionUtil, NumberUtil,
│                        JsonUtil, RandomUtil
└── smart-simulator-paperless/   (sottosistema Paperless, libreria)
    └── src/main/
        ├── java/com/capgemini/smart/simulator/paperless/
        │   ├── config/               (wiring a livello di modulo)
        │   ├── practice/             (area)
        │   │   └── configure/        (API, struttura esagonale completa)
        │   └── document/             (area)
        │       └── signature/        (API, struttura esagonale completa)
        └── resources/paperless-config.yml   (valori comuni del sottosistema)
            paperless-config-dev.yml, paperless-config-sit.yml
                                             (broker/code MQ per ambiente)
```

Un solo processo (`smart-simulator-app`) espone i servizi di tutti i
sottosistemi, con un'unica Swagger UI. Core e sottosistemi sono solo
librerie: nessuna classe main, nessun `spring-boot-maven-plugin`, nessun
`application.yml`. I punti di ingresso REST sono classi `*Resource` dentro
ciascuna feature.

## Avvio locale

Il profilo Spring è obbligatorio: senza un profilo ammesso (`dev` o `sit`)
l'app non parte. In VSCode il profilo `dev` è impostato in `.vscode/launch.json`;
da riga di comando: `SPRING_PROFILES_ACTIVE=dev` oppure
`--spring.profiles.active=dev`.

Avviare `smart-simulator-app` con il profilo `dev`, che coincide con il
docker-compose locale (`application-dev.yml` dell'app). L'app usa:
- PostgreSQL su `localhost:5433/smart_simulator`
- Kafka su `localhost:9092`
- IBM MQ (queue manager `QM1` su `localhost(1414)`)

PostgreSQL, Kafka e IBM MQ sono necessari quando un servizio li usa (query,
pubblicazione di messaggi). In particolare, senza JPA il pool Hikari verso
PostgreSQL apre le connessioni in modo lazy: l'app può partire anche con
Postgres spento e l'errore compare alla prima chiamata che usa il database
(oggi nessun servizio lo usa).

È disponibile `docker-compose.yml` (Postgres 16, Kafka in modalità KRaft,
IBM MQ developer edition) con credenziali/porte coerenti con i default:

```
docker compose up -d
```

Per un semplice smoke test senza infrastruttura si può aggiungere
temporaneamente `spring.main.lazy-initialization: true` in `application-dev.yml`
dell'app, così i bean vengono creati solo al primo utilizzo.

L'app gira sulla porta **9082**; Swagger UI su
`http://localhost:9082/swagger-ui.html`.

## Decisioni consolidate

- groupId Maven e package radice: `com.capgemini.smart.simulator` (company: `com.capgemini`).
- Spring Boot 3.3.x, Java 21, nessuna security.
- Database PostgreSQL `smart_simulator`; uno schema per sottosistema simulato
  (es. `paperless`) più uno schema tecnico
  trasversale (`smart_simulator`) per audit/config/correlazione.
- Nessuna query cross-schema: l'accesso tra sottosistemi avviene solo tramite
  i servizi REST/SOAP esposti.
- Configurazione per ambiente: profili Spring `dev` e `sit` (`dev` = docker-compose
  locale), attivati solo dall'esterno, nessun profilo di default (senza profilo
  ammesso l'app non parte). File `application-<env>.yml` nell'app e
  `<sottosistema>-config-<env>.yml` nei sottosistemi; credenziali nei file di
  ambiente (vedi CODING-STANDARDS, "Configurazione per ambiente").
- Schema e tabelle creati tramite script SQL gestiti a parte in fase di
  provisioning (niente Flyway/Liquibase).
- Accesso al database con Spring JDBC (`JdbcTemplate`), SQL scritto a mano con
  lo schema esplicito; nessun Hibernate/JPA.
- REST e SOAP possono coesistere sullo stesso sottosistema (mix libero).
- SOAP: Apache CXF, approccio **contract-first** a partire dai WSDL reali
  (generazione stub via `cxf-codegen-plugin` nel pom del singolo modulo,
  quando il WSDL sarà disponibile).
- Ogni operazione ha una request DTO dedicata. I valori non specificati e non
  obbligatori vengono risolti tramite `DefaultsResolver` (lettura da
  `smart-simulator.defaults.<sottosistema>`); se un valore diventa un campo
  obbligatorio, il `DefaultsResolver` per quel campo viene rimosso.
- Ogni campo enum è enum anche nel DTO; un valore non convertibile al tipo del
  campo è gestito dall'handler del core (HTTP 400 con `ApiError`).

## Aggiungere un nuovo sottosistema simulato

1. Creare un nuovo modulo Maven **libreria** (es. `smart-simulator-<sottosistema>`),
   aggiunto alla sezione `<modules>` del pom padre, con dipendenza su
   `smart-simulator-core`. Niente classe main, niente `spring-boot-maven-plugin`,
   niente `application.yml`.
2. Aggiungere il modulo come dipendenza di `smart-simulator-app`.
3. Se il sottosistema espone SOAP: aggiungere il WSDL sotto
   `src/main/resources/wsdl/` e configurare `cxf-codegen-plugin` per generare
   gli stub in fase di build.
4. Creare il file di configurazione dedicato `<nome-sottosistema>-config.yml`
   in `src/main/resources` del modulo (valori comuni, sezione
   `smart-simulator.defaults.<nome-sottosistema>`) e un file
   `<nome-sottosistema>-config-<env>.yml` per ciascun ambiente (`dev`, `sit`)
   con broker/coda MQ e topic Kafka, e importarli in `application.yml`
   dell'app con `spring.config.import`. Le chiavi
   `queueMap`/`topicMap`/`brokerConfigMap` devono essere uniche tra sottosistemi
   e identiche in ogni ambiente.
5. Organizzare il modulo per **area → API** (`<area>/<api>`), con struttura
   esagonale completa per ogni API (vedi `ARCHITECTURE.md` e CODING-STANDARDS): adapter di persistenza JDBC
   (`JdbcTemplate`) sullo schema dedicato, `Resource` REST e/o endpoint SOAP, DTO per ciascuna operazione.
6. Riusare `KafkaPublisherService` / `MqPublisherService` / `DefaultsResolver`
   dal core, con un producer/port/adapter dedicato per funzionalità.

## Ancora da definire

- Prossimo sottosistema da aggiungere (dopo la verifica della struttura a boot unico).
- Nomi effettivi di topic Kafka e code IBM MQ per i sottosistemi futuri.
- Valori degli ambienti diversi da `dev` per i file `application-sit.yml` e
  `paperless-config-sit.yml` (da fornire).
- Script SQL di provisioning per lo schema tecnico e per i sottosistemi.
