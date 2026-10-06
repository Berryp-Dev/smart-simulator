# PROMPT per Claude Code — Configurazione per ambiente (profili `dev` e `sit`; in questo passo solo `dev`)

## Prima di iniziare (a cura dell'utente)

1. Fare un commit del repository (il codice non è sotto git: senza commit non c'è modo di tornare indietro).
2. Chiudere l'IDE o disabilitare le estensioni Java e Spring Boot Tools del workspace, per evitare che il language server ricrei `target/` mentre si lavora.
3. Questo prompt realizza solo l'ambiente `dev`. I file di `sit` si creeranno in un secondo passo, quando saranno disponibili i valori.
4. Allegare a Claude Code: `SmartSimulator-CODING-STANDARDS.md` (versione aggiornata, sezione "Configurazione per ambiente"), `ARCHITECTURE.md`, `README.md`, `SmartSimulator-PROJECT-CONTEXT.md`.

## Regole di esecuzione (valgono per tutto il prompt)

- **Non prendere decisioni autonome.** Applica solo ciò che è scritto qui e negli standard allegati.
- **Fermati e chiedi** in caso di ambiguità o di casi non coperti, invece di scegliere. Se lavori in auto-mode, segnala esplicitamente nel riepilogo finale ogni scelta presa per ambiguità, così da poterla rivedere.
- Riferimenti: `SmartSimulator-CODING-STANDARDS.md`, sezioni "Struttura dei moduli e avvio", "Gestione configurazione multi-modulo" e "Configurazione per ambiente". Il core contiene solo lo schema di configurazione, mai i valori: non modificarlo.
- Il perimetro è solo la configurazione. Non modificare codice Java del core né dei sottosistemi, `docker-compose.yml`, né i file di specifica.
- Gli standard descrivono lo stato **target**, non quello attuale del repository: parti dal Passo 0.

## Obiettivo

Il software deve usare a runtime i file di configurazione dell'ambiente in cui gira, selezionato con il profilo Spring. Ambienti ammessi: `dev` e `sit`. L'ambiente `dev` è lo stack locale del docker-compose; non esiste un profilo `local`. **In questo passo si realizza solo `dev`**: i file di configurazione di `sit` NON vengono creati ora (i valori arriveranno più avanti). Il profilo `sit` resta però tra quelli ammessi dal controllo all'avvio.

## Regole di design da applicare (già concordate)

1. Profilo attivato solo dall'esterno. Nei file nel jar non devono comparire `spring.profiles.active` né `spring.profiles.default`: nessun profilo di default.
2. All'avvio deve essere attivo **esattamente uno** tra `dev` e `sit`. Se manca, se non è tra quelli ammessi o se ne sono attivi più di uno, l'app non parte. Il messaggio d'errore è chiaro ed elenca i profili ammessi.
3. File dell'app: `application.yml` contiene solo ciò che è identico in tutti gli ambienti più `spring.config.import`; `application-dev.yml` contiene i valori che dipendono dall'ambiente (`application-sit.yml` seguirà con la stessa struttura).
4. File del sottosistema paperless, **file separati**: `paperless-config.yml` (valori comuni) e `paperless-config-dev.yml` (`paperless-config-sit.yml` seguirà con la stessa struttura).
5. **Criterio di classificazione.** Dipende dall'ambiente, e va quindi nei file di ambiente: host, porte, utenti, password, nomi di queue manager/canale, nomi di code, topic e broker, URL del datasource, livelli di log, abilitazione Swagger. Resta nei file base solo ciò che è identico in tutti gli ambienti (es. `smart-simulator.defaults.paperless`). Nel file di sottosistema i blocchi `brokerConfigMap`/`queueMap`/`topicMap` vanno **interi** nei file di ambiente. Se un valore non è classificabile con questo criterio, fermati e chiedi.
6. Le chiavi di `queueMap`/`topicMap`/`brokerConfigMap` restano identiche a oggi (in particolare `paperlessPracticeConfigure` e `paperlessDocumentSignature`): cambiano solo dove sono definite, non i nomi né i valori di `dev`.
7. **Credenziali in chiaro nei file di ambiente**, insieme a utenti, host e porte. Nessun placeholder `${...}`, nessuna variabile d'ambiente obbligatoria.
8. **I valori di `dev` sono quelli attualmente presenti nei file di configurazione**, copiati letteralmente da `application.yml` e `paperless-config.yml` così come sono oggi. Non prenderli dalla documentazione (README, PROJECT-CONTEXT, specifiche), non correggerli, non uniformarli, non aggiungerne di nuovi. Se un valore nei file differisce da quanto riportato nella documentazione, vale il file: segnalalo nel riepilogo.
9. Il comportamento con il profilo `dev` deve essere **identico** a quello attuale: stessi valori effettivi per ogni proprietà, ad esempio la porta, le code e il broker di Configure e Signature, come risultano dalla baseline del Passo 0.

## Ambiente `sit`: rimandato

Non creare `application-sit.yml` né `paperless-config-sit.yml`, non inventare valori `sit` e non lasciare file segnaposto. Struttura e chiavi dei file di `dev` devono però essere tali da poter essere replicate per `sit` cambiando solo i valori (regola 6).

## Passo 0 — Leggere lo stato reale (prima di modificare)

Leggi dai file lo stato attuale, senza assumere che coincida con gli standard o con questo prompt:

1. `smart-simulator-app`: `application.yml` e qualsiasi altro file in `src/main/resources`; il pom (eventuale filtraggio delle risorse).
2. `smart-simulator-paperless`: `paperless-config.yml` — verifica dove si trova **davvero** (la documentazione lo colloca sia in `src/main/resources` sia nella radice del modulo) e come è importato da `spring.config.import`.
3. `.vscode/launch.json`, `docker-compose.yml` (sola lettura).
4. Test esistenti che avviano il contesto Spring (`@SpringBootTest` o simili).
5. Per ogni valore di configurazione trovato, indica in quale file sta oggi e se, secondo il criterio della regola 5, è comune o dipende dall'ambiente.

**Baseline.** Prima di modificare qualsiasi file, ottieni i valori effettivi legati oggi per queste proprietà e conservali per il confronto finale: porta del server, URL/utente/password del datasource, parametri di connessione IBM MQ, bootstrap Kafka, e per Configure e Signature broker e coda risolti dalle mappe di paperless. Indica quale metodo hai usato.

**Riepilogo del Passo 0.** Riporta in modo sintetico lo stato trovato e la classificazione dei valori. Fermati e chiedi se qualcosa diverge da quanto descritto qui in modo che incida sul lavoro.

## Task A — File di configurazione

1. Dividi l'`application.yml` dell'app secondo le regole 3 e 5: nel file base resta solo ciò che è identico in ogni ambiente e `spring.config.import`; i valori attuali dipendenti dall'ambiente vanno in `application-dev.yml`, copiati letteralmente dai file di oggi (regola 8).
2. Dividi `paperless-config.yml` secondo le regole 4, 5 e 6: nel file base i valori comuni; in `paperless-config-dev.yml` i blocchi `brokerConfigMap`/`queueMap`/`topicMap` con i valori attuali, copiati letteralmente (regola 8).
3. Fai in modo che a runtime vengano caricati i file di ambiente del profilo attivo, anche per i file importati con `spring.config.import`. Prova **prima** il comportamento standard di Spring Boot. **Se i file `paperless-config-<profilo>.yml` non vengono caricati, fermati** e riporta l'evidenza (valori attesi contro valori letti) insieme alle alternative, senza sceglierne una: A) import esplicito nell'`application.yml` con `${spring.profiles.active}`; B) import dai file `application-<env>.yml`.
4. Verifica che i file di ambiente siano inclusi nel jar eseguibile dell'app.

## Task B — Controllo del profilo all'avvio

1. Implementa il fail-fast delle regole 1 e 2 nel modulo `smart-simulator-app`, con un `EnvironmentPostProcessor` (registrato nel modo standard di Spring Boot, così interviene prima della creazione dei bean). Classe nel package `com.capgemini.smart.simulator.config` del modulo app. Se in quel modulo esiste già una convenzione diversa per le classi di configurazione, fermati e chiedi.
2. L'elenco dei profili ammessi (`dev`, `sit`) sta in un solo punto del codice, non duplicato.
3. Il messaggio d'errore dice quale profilo è stato trovato (o che manca) ed elenca i profili ammessi.
4. Naming e commenti secondo gli standard: identificatori in inglese, Javadoc/commenti in italiano, funzioni brevi e a responsabilità singola, prefissi dei metodi `get`/`insert`/`update`/`count`/`is`/`has`, altrimenti un verbo coerente (`validate`, `resolve`, ...).
5. Se esistono test che avviano il contesto Spring, fai in modo che attivino esplicitamente `dev` (`@ActiveProfiles("dev")`). Se non ne esistono, non crearne.

## Task C — Avvio da VSCode

In `.vscode/launch.json` imposta il profilo `dev` per la configurazione di avvio di `smart-simulator-app` (`env` con `SPRING_PROFILES_ACTIVE=dev`). Non toccare le altre configurazioni.

## Verifiche richieste

Per i controlli sul binding usa il mezzo meno invasivo (per esempio un test o un log temporaneo). Rimuovilo a fine lavoro ed elenca nel riepilogo ciò che hai usato. Se non riesci ad avviare l'ambiente (Postgres, Kafka, IBM MQ, docker), non è un blocco: per il binding basta l'avvio con `spring.main.lazy-initialization=true` passato da riga di comando, senza modificare i file. La verifica runtime con l'infrastruttura la fa l'utente.

1. **Build** completa verde.
2. **Binding con profilo `dev`**: i valori effettivi coincidono con la baseline del Passo 0, riga per riga (porta, datasource, MQ, Kafka, broker e coda di Configure e Signature, più ogni altra proprietà spostata). Riporta il confronto; qualsiasi differenza è un errore da correggere, non da accettare.
3. **Fail-fast**: avvio senza profilo, con profilo non ammesso (es. `prod`) e con `dev,sit` insieme. In tutti e tre i casi l'app non parte e il messaggio elenca i profili ammessi. Riporta i messaggi ottenuti.
4. **Controlli con grep, limitati ai file del perimetro** (`application*.yml` dell'app e `paperless-config*.yml`):
   - nessuna occorrenza di `spring.profiles.active` o `spring.profiles.default`;
   - nessun valore dipendente dall'ambiente rimasto nei file base (cerca nei file base `localhost`, `5433`, `DEV.QUEUE`, `passw0rd`, `QM1`): l'esito atteso è zero occorrenze;
   - le chiavi `paperlessPracticeConfigure` e `paperlessDocumentSignature` compaiono in `paperless-config-dev.yml` con lo stesso nome di oggi.
5. **Confini**: `git diff` o elenco dei file modificati/creati, per mostrare che non è stato toccato nulla fuori dal perimetro (core, codice dei sottosistemi, `docker-compose.yml`, specifiche).

## Riepilogo finale richiesto

- File creati e modificati (percorso e scopo).
- Stato trovato al Passo 0 e classificazione dei valori comuni/per ambiente.
- Esito di ciascuna verifica, con il confronto baseline/dev.
- Esito della prova del caricamento dei file importati (standard funzionante oppure fermo con alternative).
- Ogni scelta presa per ambiguità, domanda aperta, o elemento temporaneo rimosso.

## Prove a mano (a cura dell'utente, con il profilo `dev` e docker-compose attivo)

Il comportamento deve essere identico a prima. Avvia l'app con il profilo `dev` e prova da Swagger (`http://localhost:9082/swagger-ui.html`):

1. **Configure** (`POST /paperless/practice/configure`) con tutti e 5 gli scenari; controlla i messaggi su `DEV.QUEUE.2`: `IdPratica` di 10 cifre, `DM_IdProcesso` di 3 cifre, `MotivoBlocco` valorizzato solo con `PRACTICE_LOCKED`, `AmbitoPratica` uguale a `scope`.
2. **Signature** (`POST /paperless/document/signature`) con i 3 scenari; controlla i messaggi su `DEV.QUEUE.1`: un messaggio per documento, stesso `IdPratica`, `CodiceFiscale` `XXXXXXXXXXXXXXXX`, un solo `PARZIALE` o `ERRORE_FIRMA` negli scenari con anomalia.
3. **Avvio senza profilo** (da riga di comando, senza `SPRING_PROFILES_ACTIVE`): l'app non parte e il messaggio elenca `dev` e `sit`. Non provare l'avvio con `sit`: i suoi file di configurazione arriveranno in un secondo passo.
4. **Avvio da VSCode** (Boot Dashboard): parte con `dev` senza ulteriori passaggi.
5. **Errori di validazione e di deserializzazione** su Swagger: risposte identiche a prima (stessi messaggi e stessi nomi di campo).
