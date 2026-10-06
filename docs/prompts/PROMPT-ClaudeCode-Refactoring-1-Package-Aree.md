# Prompt per Claude Code — Refactoring 1/2: struttura per aree (package, Resource, @Tag)

Solo spostamento di package, rinomina dei `Resource` e `@Tag`. **Nessuna modifica di logica**, endpoint, code MQ o configurazione. Il disaccoppiamento delle port in uscita è il prompt 2/2, da eseguire dopo la verifica di questo.

## Istruzioni generali (vincolanti)

- Applica **fedelmente** quanto indicato qui e in `ServicesSimulator-CODING-STANDARDS.md` (versione aggiornata allegata, sezione "Architettura esagonale — Package by Area e Feature"). **Non prendere decisioni autonome.**
- In caso di ambiguità o caso non coperto, **fermati e chiedi**; segnala ogni scelta presa.
- Non modificare file `*.md`. Identificatori in inglese, commenti/Javadoc in italiano.
- **Non creare** cartelle `common`: si creano solo quando un artefatto è realmente condiviso, e oggi non lo è.

## Passo 0 — Stato attuale (prima di modificare)

Gli standards descrivono la struttura target: **leggi lo stato reale dai file**. Riporta: l'albero dei package di `servicessimulator-paperless`; il nome e il package di ogni classe `Resource`; la presenza di `@Tag` o altre annotazioni Swagger; ogni riferimento ai package o ai nomi di classe in file di configurazione, `@ControllerAdvice` con `assignableTypes`, `package-info.java`/`jaxb.index`/contesto JAXB, test. Se lo stato reale differisce da quanto descritto sotto, **fermati e chiedi**.

## Task

1. **Sposta i package** (struttura interna di ogni API invariata: `domain`, `application`, `adapter` con le stesse sottocartelle):
   - `com.capgemini.servicessimulator.paperless.practiceconfigure` → `com.capgemini.servicessimulator.paperless.practice.configure`
   - `com.capgemini.servicessimulator.paperless.documentsignature` → `com.capgemini.servicessimulator.paperless.document.signature`
   - `config/` resta a livello di modulo.
2. **Rinomina i `Resource`** (nome = `<Sottosistema><Area><Api>Resource`), restano in `adapter/in/rest/resource` della rispettiva API:
   - il `Resource` di Configure → `PaperlessPracticeConfigureResource`
   - `PaperlessDocumentResource` → `PaperlessDocumentSignatureResource`
3. **Aggiungi `@Tag`** (`io.swagger.v3.oas.annotations.tags.Tag`) sulla classe: `@Tag(name = "Practice")` su `PaperlessPracticeConfigureResource`, `@Tag(name = "Document")` su `PaperlessDocumentSignatureResource`.
4. Aggiorna di conseguenza package declaration, import, `@ControllerAdvice(assignableTypes = ...)`, riferimenti in configurazione e test, e **qualsiasi file che porta con sé il package** (es. `package-info.java` o `jaxb.index` dei payload JAXB).
5. Non cambiare nomi di classi diverse dai due `Resource`, né endpoint, path, chiavi `queueMap`, messaggi.

## Verifica richiesta

- `mvn clean install` dalla root con successo; nessun riferimento residuo a `practiceconfigure`/`documentsignature` (cerca con grep in sorgenti, yml, test).
- Se l'ambiente è disponibile, avvia `servicessimulator-app`: la Swagger UI deve mostrare gli endpoint raggruppati in **Practice** e **Document**; una chiamata per servizio deve rispondere come prima (Configure 200 con `paperlessStatus`, Signature 200 senza body) e pubblicare su `DEV.QUEUE.2` / `DEV.QUEUE.1`. Riporta ciò che non hai potuto verificare.

## Al termine

Elenca file spostati/modificati e le scelte prese per ambiguità.
