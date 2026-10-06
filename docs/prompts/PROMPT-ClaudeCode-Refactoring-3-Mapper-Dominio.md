# Prompt per Claude Code — Refactoring 3/3: allo use case arriva solo il dominio (mapper DTO -> dominio)

**Da eseguire SOLO dopo** i prompt 1/2 e 2/2 e dopo la loro verifica.

## Istruzioni generali (vincolanti)

- Applica **fedelmente** quanto indicato qui e in `ServicesSimulator-CODING-STANDARDS.md` (versione aggiornata allegata, in particolare "Domain vs DTO"). **Non prendere decisioni autonome.**
- In caso di ambiguità o caso non coperto, **fermati e chiedi**; segnala ogni scelta presa.
- Non modificare file `*.md`. Identificatori in inglese, commenti/Javadoc in italiano. Prefissi metodi secondo gli standards.
- **Il comportamento esterno non cambia**: endpoint, risposte, **nomi dei campi e messaggi degli errori di validazione**, codici HTTP e contenuto dei messaggi MQ devono restare identici a prima.
- Non creare `DomainValidationException` né cartelle `common`.

## Problema

Oggi i due `Resource` passano il **DTO** direttamente allo use case. Lo standard prevede invece che il `Resource` converta il DTO in un oggetto di dominio tramite il **mapper** e passi allo use case **solo il dominio**: né lo use case, né il validator, né il service devono ricevere o importare un DTO (nessun import da `adapter` in `application` e `domain`).

## Passo 0 — Stato attuale (prima di modificare)

Leggi dai file e riporta, per **Configure** e per **Signature**: la firma attuale dello use case (`port/in`) e il tipo che riceve; il tipo su cui lavora il validator; quali oggetti di dominio radice esistono già in `domain/model` (nome e campi); quali metodi ha già il mapper (`adapter/in/rest/mapper`) e in che direzione; dove avviene oggi la conversione enum DTO -> enum di dominio. Elenca ogni import da `adapter` presente in `application` e `domain`. Se esistono già oggetti di dominio radice, **riusali** senza rinominarli.

## Task

Per **ciascuna API**:

1. **Oggetto di dominio radice** in `domain/model` (se non esiste già, vedi Passo 0), senza suffisso `Dto`:
   - Configure: `PaperlessPracticeConfiguration` con `practiceIdOmnichannel`, `customerDenomination`, `scenarioCode` (enum di dominio), `scope`.
   - Signature: `PaperlessDocumentSignature` con `practiceIdOmnichannel`, `scope`, `scenarioCode` (enum di dominio), `documentIds` (`List<Long>`).
2. **Mapper** (`adapter/in/rest/mapper`, usa quello esistente): metodo DTO request -> dominio, con conversione enum DTO -> enum di dominio tramite `switch` esplicito (mai `valueOf`), enum di dominio qualificato per esteso. Per i nomi dei nuovi metodi usa un verbo coerente (es. `mapToDomain`) e mantieni quelli già presenti.
   - Il mapper **non valida e non lancia eccezioni per valori mancanti**: un campo assente resta `null` nel dominio (es. `scenarioCode` null -> enum di dominio null; lista null -> lista null; elemento null -> elemento null). La validazione resta nel validator.
3. **Use case** (`port/in`) e **service**: ricevono l'oggetto di dominio, non il DTO.
4. **Validator**: lavora sul tipo di dominio. I **nomi dei campi negli errori restano quelli del contratto REST** (`practiceIdOmnichannel`, `scope`, `scenarioCode`, `customerDenomination`, `listDocuments`, `listDocuments[i]`) e i **messaggi restano identici** (es. "Campo obbligatorio"), anche se nel dominio il campo si chiama diversamente (es. `documentIds`).
5. **Resource**: converte con il mapper e passa **solo il dominio** allo use case. Non importa più dettagli dell'application che richiedono il DTO.
6. Se altri punti dell'application usano ancora tipi DTO, **fermati e chiedi**.

## Verifica richiesta

- `mvn clean install` dalla root con successo.
- **Controllo dei confini**: grep per verificare che in `application` e `domain` di entrambe le API non compaia nessun import da `adapter` (né `dto`, né `mapper`, né JAXB). Riporta l'esito.
- Se l'ambiente è disponibile, **prima di modificare** registra le risposte (status e body completo) ai casi seguenti, poi riesegui gli stessi dopo la modifica e **confrontale**: devono essere identiche. Casi per Signature: `{}`; `scenarioCode` non ammesso; `listDocuments: []`; `listDocuments: [1, null]`; `listDocuments: ["abc"]`; chiamata valida con `ALL_SIGNED`. Casi per Configure: `{}`; `scenarioCode` non ammesso; chiamata valida con `PRACTICE_LOCKED`. Riporta ciò che non hai potuto verificare.

## Al termine

Elenca file creati/modificati, scelte prese per ambiguità e l'esito del controllo dei confini.
