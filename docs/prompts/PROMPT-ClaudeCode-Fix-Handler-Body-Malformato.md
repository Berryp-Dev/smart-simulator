# Prompt per Claude Code — Corpo della richiesta non leggibile: 400 invece di 500

## Istruzioni generali (vincolanti)

- Applica **fedelmente** quanto indicato qui e in `ServicesSimulator-CODING-STANDARDS.md` (versione aggiornata allegata, sezione "Gestione errori"). **Non prendere decisioni autonome.**
- In caso di ambiguità o caso non coperto, **fermati e chiedi**; segnala ogni scelta presa.
- Modifica **solo** `GlobalExceptionHandler` (core). Non toccare file `*.md`, né altre classi.
- Identificatori in inglese, commenti/Javadoc in italiano, prefissi metodi secondo gli standard.

## Problema

Oggi `handleMessageNotReadable` gestisce solo le cause `InvalidFormatException`; per tutte le altre cause di `HttpMessageNotReadableException` (JSON malformato, body assente, tipo sbagliato per un'intera struttura, es. `"listDocuments": "abc"`) delega a `handleGeneric` e risponde **HTTP 500**.

## Modifica richiesta

Per ogni `HttpMessageNotReadableException` la cui causa **non** è `InvalidFormatException`, rispondi **HTTP 400** con `ApiError` contenente **un solo** `ValidationError`:

- `field`: il percorso del campo se la causa è una `JsonMappingException` con path non vuoto (riusa la logica già presente per ricavare il percorso, es. `listDocuments`); altrimenti `null`;
- `message`: testo fisso `"Corpo della richiesta non valido o malformato"`. **Non** includere il messaggio di Jackson né nomi di classi interne;
- log a livello **`warn`** (errore del chiamante), non `error`.

Resta **invariato** il comportamento per `InvalidFormatException` (enum e tipi non convertibili) e per tutte le altre eccezioni non gestite (`handleGeneric`, HTTP 500).

Se `ValidationError` non ammette `field` null, o il percorso non è ricavabile in un modo coerente con il codice esistente, **fermati e chiedi**.

## Verifica richiesta

Compila il progetto. Se l'ambiente è disponibile, verifica su `POST /paperless/document/signature` con body:
- JSON malformato (es. `{"scope":`) → 400, `field` null
- body assente → 400
- `{"practiceIdOmnichannel":"A","scope":"B","scenarioCode":"ALL_SIGNED","listDocuments":"abc"}` → 400, `field` = `listDocuments`
- `listDocuments: ["abc"]` → 400 con `InvalidFormatException`, `field` = `listDocuments[0]` (comportamento invariato)

## Al termine

Mostra il diff di `GlobalExceptionHandler` ed elenca le scelte prese per ambiguità.
