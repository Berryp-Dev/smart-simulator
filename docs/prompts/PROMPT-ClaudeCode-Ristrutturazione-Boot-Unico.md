# Prompt per Claude Code — Ristrutturazione a boot unico

**Da eseguire SOLO dopo** il prompt `PROMPT-ClaudeCode-Paperless-Signature.md` e dopo la sua verifica.

## Istruzioni generali (vincolanti)

- Applica **fedelmente** quanto indicato qui e in `ServicesSimulator-CODING-STANDARDS.md` (versione aggiornata allegata, sezioni "Struttura dei moduli e avvio" e "Gestione configurazione multi-modulo"). **Non prendere decisioni autonome.**
- In caso di ambiguità o caso non coperto, **fermati e chiedi**. Se sei in auto-mode, segnala esplicitamente ogni scelta presa.
- Non modificare file `*.md`. Alla fine **elenca** i punti di `README.md` e `ARCHITECTURE.md` che diventano obsoleti, senza modificarli.
- **Non creare** il modulo `servicessimulator-raccordo-account`: è fuori scopo.
- Nessuna modifica al codice delle feature (domain/application/adapter) e alla logica dei servizi: è una ristrutturazione di build, avvio e configurazione.

## Obiettivo

Oggi `servicessimulator-core` e `servicessimulator-paperless` sono due `@SpringBootApplication` separati. Si vuole **un solo modulo avviabile** con tutti i servizi; core e paperless diventano **solo librerie**.

## Task

1. **Nuovo modulo `servicessimulator-app`** (aggiungilo al `pom.xml` parent, groupId `com.capgemini`):
   - dipende da `servicessimulator-core` e `servicessimulator-paperless`;
   - **unico** `spring-boot-maven-plugin` (repackage) e unica classe `@SpringBootApplication`, `ServicesSimulatorApplication`, in `com.capgemini.servicessimulator` (la scansione deve vedere core e tutti i sottosistemi);
   - riporta sulla nuova classe main eventuali annotazioni presenti sulle main rimosse (es. `@EnableConfigurationProperties`, `@ConfigurationPropertiesScan`); se ci sono conflitti o `@ComponentScan`/`@EntityScan`/`@EnableJpaRepositories` espliciti, **fermati e chiedi**.
2. **`servicessimulator-core` → solo libreria**: rimuovi `ServicesSimulatorCoreApplication`, il `spring-boot-maven-plugin` e il suo `application.yml`.
3. **`servicessimulator-paperless` → solo libreria**: rimuovi la classe main, il `spring-boot-maven-plugin` e il suo `application.yml`.
4. **Configurazione**:
   - `servicessimulator-app/src/main/resources/application.yml`: valori condivisi spostati dagli `application.yml` di core e paperless (datasource, connessione IBM MQ, bootstrap Kafka, ecc.) e **`server.port: 9082`** (la 9081 del core sparisce);
   - `servicessimulator-paperless/src/main/resources/paperless-config.yml`: tutto ciò che è specifico del sottosistema (broker `brokerConfigMap`, `queueMap` con le chiavi dedicate già presenti, eventuali `servicessimulator.defaults.paperless`);
   - l'`application.yml` dell'app importa il file con `spring.config.import: classpath:paperless-config.yml`;
   - i valori restano **identici** a quelli attuali: cambia solo dove stanno. Se trovi un valore di cui non è chiaro se sia condiviso o specifico, o un file dedicato già importato via `spring.config.import` dal core, **fermati e chiedi**.
5. **Build**: core e paperless devono compilare come jar semplici, con le dipendenze (starter web, springdoc ecc.) ancora risolte; segnala eventuali dipendenze che prima arrivavano dal plugin/boot e ora mancano. Se ci sono test che dipendono dalle main rimosse, **fermati e chiedi**.
6. **Swagger**: nessun raggruppamento per sottosistema in questa fase.

## Verifica richiesta

- `mvn clean install` dalla root conclude con successo.
- Se l'ambiente (docker compose) è disponibile, avvia `servicessimulator-app` e verifica: porta 9082, `/swagger-ui.html` mostra gli endpoint di Configure e Signature, un messaggio MQ viene pubblicato sulla coda configurata per ciascun servizio (Configure: `DEV.QUEUE.2`; Signature: `DEV.QUEUE.1`). Altrimenti riporta cosa non hai potuto verificare.

## Al termine

Elenca file creati/modificati/eliminati, le scelte prese per ambiguità e i punti obsoleti di `README.md` e `ARCHITECTURE.md`.
