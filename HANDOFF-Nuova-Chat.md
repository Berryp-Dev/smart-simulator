# SmartSimulator — Nota di passaggio per la nuova chat

Da incollare (o allegare) all'inizio della nuova chat insieme ai file del pacchetto.

## File da allegare

1. `SmartSimulator-CODING-STANDARDS.md` — regole di codice e architettura (fonte di verità)
2. `SmartSimulator-PROJECT-CONTEXT.md` — stato del progetto
3. `SmartSimulator-SERVICES-SPECIFICATION.md` — specifiche dei servizi implementati
4. `ARCHITECTURE.md` e `README.md` — sintesi della struttura

## Stato del progetto in due righe

- Un solo modulo avviabile (`smart-simulator-app`, porta 9082); `smart-simulator-core` e `smart-simulator-paperless` sono librerie.
- Persistenza con `JdbcTemplate` (nessun Hibernate/JPA). Struttura per area → API con esagono completo per API, port in uscita senza JAXB/DTO, allo use case arriva solo il dominio tramite mapper. Paperless (Configure e Signature) è implementato, refactorizzato e verificato; resta solo da controllare a mano il contenuto dei messaggi MQ (vedi `PROJECT-CONTEXT.md`).

## Prossimo obiettivo

Da definire all'inizio della nuova chat (nuovo sottosistema o nuovi servizi).

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
