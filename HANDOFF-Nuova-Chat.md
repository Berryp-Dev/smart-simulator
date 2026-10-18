# SmartSimulator — Nota di passaggio per la nuova chat

Da incollare (o allegare) all'inizio della nuova chat insieme ai file del pacchetto.

## File da allegare

1. `SmartSimulator-CODING-STANDARDS.md` — regole di codice e architettura (fonte di verità)
2. `SmartSimulator-PROJECT-CONTEXT.md` — stato del progetto
3. `SmartSimulator-SERVICES-SPECIFICATION.md` — specifiche dei servizi implementati
4. `ARCHITECTURE.md` e `README.md` — sintesi della struttura

## Stato del progetto in due righe

- Un solo modulo avviabile (`smart-simulator-app`, porta 9082); `smart-simulator-core`, `smart-simulator-paperless` e `smart-simulator-document-viewer` sono librerie.
- Persistenza con `JdbcTemplate` (nessun Hibernate/JPA). Struttura per area → API con esagono completo per API, port in uscita senza JAXB/DTO, allo use case arriva solo il dominio tramite mapper. Paperless (Configure e Signature) è implementato, refactorizzato e verificato; resta solo da controllare a mano il contenuto dei messaggi MQ (vedi `PROJECT-CONTEXT.md`).

- Direttive aggiornate il 17/10/2026 (`RestResource`, enum con `valueOf`, `Service`/`ServiceImpl`, `@Autowired` sul campo): allineato e verificato (nessuna divergenza in `paperless` e `document-viewer`).

- Context-path `/smart-simulator` per l'intera app (endpoint e Swagger UI sotto questo prefisso). Nuovo modulo `smart-simulator-document-viewer` (struttura standard sottosistema → area `document` → API `getstatic`, con `common` di modulo): servizio getStatic `GET /smart-simulator/document-viewer/document/getStatic?productCode=...` implementato, con i PDF nelle risorse del classpath del modulo (`src/main/resources/pdf/static/`, in tutti gli ambienti, rebuild per aggiungerne uno); verificato a mano il 2026-10-18.

## Prossimo obiettivo

Da definire: prossimo sottosistema o servizio da implementare.

Punti aperti:
- Con `Accept: application/xml` gli errori 400 di validazione rispondono 500 (corpo JSON non scrivibile) e il 406 non ha corpo. Bassa priorità, non risolto.
- Aggiornare il changelog e la versione in `OpenApiConfiguration` è a cura di Berry a fine di ogni intervento: ricordarglielo sempre.

## Metodo di lavoro (da rispettare)

1. Io fornisco le specifiche. Tu proponi struttura/design **senza scrivere codice**.
2. Io confermo o correggo. Solo dopo conferma esplicita prepari il **prompt per Claude Code** (mai il codice direttamente).
3. Ogni prompt per Claude Code include: istruzione di **non prendere decisioni autonome** e di **fermarsi a chiedere** in caso di ambiguità; richiamo agli standards; un **Passo 0** che fa leggere dai file lo **stato reale** prima di modificare (gli standards descrivono il target, non lo stato attuale); verifiche richieste (build, controllo dei confini con grep, test); riepilogo finale con le scelte prese per ambiguità.
4. Quando Claude Code segnala scelte o domande, le valuti **solo sulla base di quanto già concordato**, proponi una raccomandazione e lasci a me la decisione.
5. Se Claude Code non può avviare l'ambiente (IBM MQ, docker), la verifica runtime la faccio io: i prompt devono elencare i casi da provare.
6. Non modificare file né eseguire comandi sul progetto senza mia conferma. Quando cambiano standard o specifiche, aggiornare i `.md` e riconsegnarli.

## Lezioni dal lavoro precedente

- Claude Code a volte scambia la struttura target descritta negli standards per lo stato attuale del repository: il Passo 0 serve a evitarlo.
- Un criterio di verifica deve corrispondere al perimetro del prompt (un controllo con grep troppo largo falliva su violazioni preesistenti fuori scope).
- Il codice non è sotto git: fare un commit prima di ogni refactoring ampio.
- Per rinomine di directory: chiudere l'IDE o disabilitare le estensioni Java e Spring Boot Tools del workspace (il language server ricrea `target/` e blocca le cartelle), e verificare i processi `java.exe` di `redhat.java`.
- Messaggi d'errore e nomi di campo del contratto devono restare identici dopo i refactoring: confrontare le risposte prima/dopo.
- Con gli YAML su disco non farli ricreare da testo: il prompt indica il percorso e Claude Code verifica gli sha256 (i file possono essere untracked in git, quindi `git diff` non basta).
- Su Windows servono `git config core.longpaths true` (percorsi lunghi) e, prima di rinominare directory, la chiusura dell'IDE.
- Chiedere sempre un commit prima di un intervento ampio: Claude Code può trovare l'albero sporco o una modifica annullata da un revert.
- Non terminare i processi Java per nome: solo il PID dell'app avviata da te.
- Verificare l'app sempre su un jar prodotto da `mvn clean install`, non da `target/classes` compilato dall'IDE (può dare "Unresolved compilation problems").
- Se l'albero è sporco il Passo 0 si ferma: fare prima il commit del lavoro precedente.
- Quando YAML e software divergono, vince lo YAML: il software non aggiunge controlli né campi.
- Committare insieme codice e `.md` dello stesso intervento: i prompt per Code si fermano se l'albero non è pulito.
