# Proposta — Struttura dei package per aree (sottosistema paperless)

Documento di confronto, **in attesa di decisione**. Non è ancora uno standard: quando si sceglie una delle due strutture, `CODING-STANDARDS.md`, `ARCHITECTURE.md`, `README.md`, `PROJECT-CONTEXT.md` e `SERVICES-SPECIFICATION.md` vanno aggiornati di conseguenza.

## 1. Situazione attuale

Ogni API è una feature con un solo livello di package, sotto il sottosistema:

```
paperless/
├── config/                  (configurazione di modulo)
├── practiceconfigure/       (API configure, area e API nello stesso nome)
│   ├── adapter/
│   ├── application/
│   └── domain/
└── documentsignature/       (API signature, area e API nello stesso nome)
    ├── adapter/
    ├── application/
    └── domain/
```

Gli endpoint hanno già una forma a tre livelli, `/paperless/<area>/<api>`:
`/paperless/practice/configure` e `/paperless/document/signature`.

**Limiti**
- Area e API sono fusi in un unico nome (`practiceconfigure`, `documentsignature`).
- Non esiste un posto naturale per le parti comuni a più API della stessa area.
- Il `Resource` attuale `PaperlessDocumentResource` ha il nome dell'area ma serve una sola API.

## 2. Principi comuni a entrambe le strutture

- **L'esagono resta per singola API**: dentro ogni API restano `domain`, `application`, `adapter`, con le stesse cartelle, classi e nomi di classe di oggi (che includono già area e API, es. `PaperlessDocumentSignatureValidator`).
- **`common` solo quando serve**: la cartella si crea nel momento in cui una seconda API della stessa area ha bisogno di qualcosa che oggi è in una sola. Oggi non c'è nulla da condividere, quindi non si creano cartelle vuote.
- **Livelli di condivisione**: API → `<area>/common` → `common` del sottosistema → core. Per il codice trasversale a tutto il progetto resta valido il core (`core/util`).
- **Regola di dipendenza**: un'API può usare il `common` della sua area e quello del sottosistema, ma **mai le classi di un'altra API**, nemmeno della stessa area.
- **Nessun impatto funzionale**: endpoint, code MQ, configurazione e logica non cambiano. Il package radice resta `com.capgemini.servicessimulator`, quindi la scansione dell'app non cambia.

## 3. Struttura A — un Resource per ogni API

```
paperless/                         Sottosistema paperless
├── config/                        Configurazione di modulo
├── common/                        Comune a più aree (solo se serve)
├── practice/                      Area pratica
│   ├── common/                    Comune alle API di practice (solo se serve a 2+)
│   └── configure/                 API configure
│       ├── domain/ application/   Esagono dell'API
│       └── adapter/in/rest/       Resource + DTO + mapper dell'API
└── document/                      Area documento
    ├── common/                    Comune alle API di document (solo se serve a 2+)
    └── signature/                 API signature
        ├── domain/ application/   Esagono dell'API
        └── adapter/in/rest/       Resource + DTO + mapper dell'API
```

Resource previsti: `PaperlessPracticeConfigureResource` e `PaperlessDocumentSignatureResource`.

**Vantaggi**
- Ogni API è autosufficiente: tutto ciò che la riguarda sta in una sola cartella, e si può togliere o spostare senza toccare le altre.
- Nessun file condiviso tra API della stessa area: aggiungere un'API non modifica codice esistente.
- Coerente con la regola degli standards "il nome del `Resource` include la feature".

**Svantaggi**
- Più classi `Resource` (una per API).
- Gli endpoint della stessa area sono sparsi in più cartelle, quindi per vederli tutti serve Swagger o una ricerca.

## 4. Struttura B — un Resource per ogni area

```
paperless/                         Sottosistema paperless
├── config/                        Configurazione di modulo
├── common/                        Comune a più aree (solo se serve)
├── practice/                      Area pratica
│   ├── rest/                      Resource dell'area (tutte le API di practice)
│   ├── common/                    Comune alle API di practice (solo se serve a 2+)
│   └── configure/                 API configure
│       ├── domain/ application/   Esagono dell'API
│       └── adapter/in/rest/       Solo DTO + mapper dell'API
└── document/                      Area documento
    ├── rest/                      Resource dell'area (tutte le API di document)
    ├── common/                    Comune alle API di document (solo se serve a 2+)
    └── signature/                 API signature
        ├── domain/ application/   Esagono dell'API
        └── adapter/in/rest/       Solo DTO + mapper dell'API
```

Resource previsti: `PaperlessPracticeResource` e `PaperlessDocumentResource`.

In questa variante si assume che DTO e mapper restino nell'API. Se si spostassero anche questi nell'area, le API perderebbero l'autosufficienza.

**Vantaggi**
- Meno classi `Resource`: tutti gli endpoint di un'area sono in un solo file.
- Vicino allo schema di partenza (`PaperlessDocumentResource`).

**Svantaggi**
- Una parte dell'adapter di ingresso esce dalla cartella dell'API, che non è più autosufficiente.
- Ogni nuova API obbliga a modificare il `Resource` dell'area: è un file condiviso e un possibile punto di conflitto.
- Il `Resource` dell'area dipende dai casi d'uso di tutte le API dell'area.

## 5. Confronto

| Aspetto | A: Resource per API | B: Resource per area |
|---|---|---|
| Autosufficienza dell'API | Completa | Parziale (il Resource è fuori) |
| Aggiungere una nuova API | Solo cartelle nuove | Nuove cartelle + modifica del Resource d'area |
| Numero di classi Resource | Una per API | Una per area |
| Endpoint di un'area | In più cartelle | In un solo file |
| Coerenza con gli standards attuali | Totale (nome del Resource = feature) | Da modificare (nome del Resource = area) |
| Rischio di conflitti | Basso | Più alto sul Resource d'area |

## 6. Raccomandazione

**Struttura A.** È la più vicina all'obiettivo "tutto suddiviso per singola API" e richiede meno modifiche agli standards.

## 7. Impatto della migrazione (a decisione presa)

- **Spostamento di package**: `practiceconfigure` → `practice.configure` e `documentsignature` → `document.signature`. Con la struttura A si rinominano anche i `Resource`: `PaperlessDocumentResource` → `PaperlessDocumentSignatureResource`, e quello di Configure secondo la stessa regola.
- **Documenti da aggiornare**: `CODING-STANDARDS.md` (albero e regole di dipendenza), `ARCHITECTURE.md`, `README.md`, `PROJECT-CONTEXT.md`, `SERVICES-SPECIFICATION.md` (nomi dei package).
- **Prompt per Claude Code**: uno breve, limitato allo spostamento dei package e alla rinomina dei `Resource`, senza modifiche di logica.
- **Nuovo modulo account-raccordo-partitario**: partirebbe già con la struttura scelta. I suoi URL hanno già due gruppi naturali, `ui-app/raccordo/sales/current-accounts/...` e `smartengine/raccordo/...`, che potrebbero diventare le aree. Da decidere quando si definisce il modulo.

## 8. Decisione da prendere

Scegliere tra A e B (raccomandata: A).
