# Prompt per Claude Code — Rinomina del modello di dominio di Configure

Rinomina meccanica, **nessuna modifica di logica**.

## Istruzioni generali (vincolanti)

- Applica **fedelmente** quanto indicato qui e in `ServicesSimulator-CODING-STANDARDS.md` (versione aggiornata allegata, "Convenzioni di naming", voce "Modelli di dominio": nessun suffisso tecnico). **Non prendere decisioni autonome.**
- In caso di ambiguità fermati e chiedi. Non modificare file `*.md`.

## Task

1. Rinomina la classe `PaperlessPracticeConfigureDomain` (`practice/configure/domain/model`) in **`PaperlessPracticeConfiguration`**, con il file e tutti i riferimenti (import, firme di use case/service/validator/mapper/Resource, Javadoc, variabili e parametri il cui nome derivava da `...Domain`).
2. Non rinominare nient'altro. In particolare `PaperlessDocumentSignature`, `PaperlessPracticeStatusUpdate` e `PaperlessDocumentStatusUpdate` restano come sono (già senza suffisso).
3. Cerca con grep nei sorgenti e nei test ogni occorrenza residua di `PaperlessPracticeConfigureDomain`: deve essere zero.

## Verifica

`mvn clean install` dalla root con successo. Riporta i file modificati.
