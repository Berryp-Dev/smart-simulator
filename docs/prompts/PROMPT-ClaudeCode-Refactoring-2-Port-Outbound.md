# Prompt per Claude Code — Refactoring 2/2: port in uscita senza JAXB (inversione di dipendenza)

**Da eseguire SOLO dopo** il prompt 1/2 (`PROMPT-ClaudeCode-Refactoring-1-Package-Aree.md`) e dopo la sua verifica.

## Istruzioni generali (vincolanti)

- Applica **fedelmente** quanto indicato qui e in `ServicesSimulator-CODING-STANDARDS.md` (versione aggiornata allegata, in particolare "Port in uscita (inversione di dipendenza)"). **Non prendere decisioni autonome.**
- In caso di ambiguità o caso non coperto, **fermati e chiedi**; segnala ogni scelta presa.
- Non modificare file `*.md`. Identificatori in inglese, commenti/Javadoc in italiano. Prefissi metodi secondo gli standards.
- **Il comportamento esterno non cambia**: endpoint, response, validazione, codice dello `Stato`, e soprattutto il **contenuto dei messaggi XML** pubblicati devono restare identici a prima (unica eccezione: il testo dell'eccezione al punto 5 di Signature).
- **Non creare** `DomainValidationException` né cartelle `common`: nessuna delle due API ha invarianti di dominio o parti condivise.

## Passo 0 — Stato attuale (prima di modificare)

Leggi dai file e riporta, per **Configure** e per **Signature**: la firma attuale della `port/out` (`PublishStatusUpdatePort`, `PublishDocumentStatusUpdatePort`) e quali classi importa (JAXB/messaggio dell'adapter?); dove il service costruisce il messaggio; dove sono calcolati `IdPratica`, `DM_IdProcesso`, `MotivoBlocco`, `CodiceFiscale`; come è rappresentato oggi lo `Stato` nel dominio (enum o stringa). Se lo `Stato` di Configure è una `String` nel dominio vale la decisione già presa, indicata nei task (enum di dominio `PaperlessPracticeStatusCode`).

## Problema

La `port/out` appartiene all'application layer e **non deve importare classi dell'adapter** (JAXB, DTO, payload come `InvioAggiornamentoStato...`): deve accettare solo tipi di dominio o primitivi Java. La conversione JAXB avviene solo nell'adapter MQ.

## Task

### Configure (`practice/configure`)

1. **Nuovo enum di dominio** `PaperlessPracticeStatusCode` in `domain/enums` con i 5 valori `ATTESA_FIRMATARI`, `PRATICA_BLOCCATA`, `PRATICA_SBLOCCATA`, `ATTESA_CONTESTO_FIRMA`, `ATTESA_FIRMA_CLIENTE` (decisione presa: oggi lo `Stato` è una `String` nel dominio). Il service (`resolveStato`) restituisce questo enum al posto della stringa letterale.
   - **`PaperlessPracticeConfigureResult`** porta lo stato come `PaperlessPracticeStatusCode` (invece di `String`); il mapper in uscita lo converte nella `String` `paperlessStatus` di `PaperlessPracticeConfigureDtoResp` con `switch` esplicito. **Il JSON della response resta identico** (`{"paperlessStatus": "ATTESA_FIRMATARI"}` ecc.).
   - Nuovo oggetto di dominio `PaperlessPracticeStatusUpdate` in `domain/model` (senza suffisso `Dto`), con: id pratica, id processo, id esterno, ambito pratica, motivo blocco (**nullable**), stato (`PaperlessPracticeStatusCode`).
2. `PublishStatusUpdatePort`: il metodo (stesso nome di oggi) riceve `PaperlessPracticeStatusUpdate` invece del messaggio JAXB; nessun import dall'adapter.
3. Il **service** costruisce `PaperlessPracticeStatusUpdate`: genera `IdPratica` (10 cifre) e `DM_IdProcesso` (3 cifre) con `RandomUtil` come oggi, e calcola il testo di `MotivoBlocco` (`"Firmatario " + customerDenomination + " privo di consenso privacy"`, solo per `PRACTICE_LOCKED`, altrimenti null) **nel service**, passandolo come campo del dominio.
4. L'**adapter MQ** converte `PaperlessPracticeStatusUpdate` → messaggio JAXB `InvioAggiornamentoStato`, campo per campo, senza decisioni di business (lo stato diventa la stringa italiana con `switch` esplicito sull'enum), e lo pubblica come testo grezzo come oggi.

### Signature (`document/signature`)

1. Nuovo oggetto di dominio `PaperlessDocumentStatusUpdate` in `domain/model`, con: id pratica, id esterno, ambito pratica, id documento (`Long`), stato (`PaperlessDocumentSignatureStatusCode`).
2. `PublishDocumentStatusUpdatePort`: il metodo riceve `PaperlessDocumentStatusUpdate`; nessun import dall'adapter.
3. Il **service** costruisce un `PaperlessDocumentStatusUpdate` per ogni documento (stessa logica di oggi: `IdPratica` unico per chiamata, posizione anomala, funzione comune per lo stato).
4. L'**adapter MQ** converte in messaggio JAXB e imposta il `CodiceFiscale` fisso `XXXXXXXXXXXXXXXX`: la costante si **sposta nell'adapter** (dettaglio del messaggio legacy), non è un campo del dominio.
5. Nel messaggio dell'eccezione di `PaperlessDocumentSignatureMqAdapter` compare il testo "(documentsignature)", rimasto dal vecchio nome del package: aggiornalo in "(document.signature)". È l'unica modifica a un messaggio ammessa in questo prompt.

## Verifica richiesta

- `mvn clean install` dalla root con successo.
- **Controllo dei confini (perimetro di questo prompt)**: con grep verifica che (1) in `domain` e in `application/port/out` di entrambe le API non compaia nessun import da `adapter`; (2) in tutto `domain` e `application` non compaia nessuna classe JAXB (`jakarta.xml.bind`, `InvioAggiornamentoStato`). Riporta l'esito.
- **Violazione nota, fuori scope**: i service e i validator importano ancora DTO e mapper di `adapter.in.rest`. Non correggerla: la risolve il prompt 3/3. Riportala soltanto come violazione preesistente (elenco degli import).
- Se l'ambiente è disponibile, avvia l'app, esegui una chiamata per scenario (Configure: tutti e 5 gli `scenarioCode`, in particolare `PRACTICE_LOCKED` con `MotivoBlocco` valorizzato; Signature: `ALL_SIGNED`, `PARTIAL_SIGNATURE`, `SIGNATURE_ERROR`) e verifica che i messaggi su `DEV.QUEUE.2` / `DEV.QUEUE.1` abbiano **lo stesso contenuto XML di prima**. Riporta ciò che non hai potuto verificare.

## Al termine

Elenca file creati/modificati, scelte prese per ambiguità e l'esito del controllo dei confini.
