# Prompt per Claude Code — Rimozione di Hibernate/JPA, adozione di JdbcTemplate

**Da eseguire SOLO dopo** la ristrutturazione a boot unico (`PROMPT-ClaudeCode-Ristrutturazione-Boot-Unico.md`) e dopo la sua verifica.

## Istruzioni generali (vincolanti)

- Applica **fedelmente** quanto indicato qui e in `ServicesSimulator-CODING-STANDARDS.md` (sezione "Persistenza"). **Non prendere decisioni autonome.**
- In caso di ambiguità o caso non coperto, **fermati e chiedi**; segnala ogni scelta presa.
- Non modificare file `*.md`. Non creare adapter di persistenza né classi nuove: non esiste ancora nessuna entità/tabella da gestire.
- Non toccare le feature (domain/application/adapter) né la logica dei servizi.

## Decisione

Il progetto **non usa Hibernate/JPA**: l'accesso al database avverrà con Spring JDBC (`JdbcTemplate`), SQL scritto a mano, schema esplicito nell'SQL di ogni adapter.

## Passo 0 — Stato attuale (prima di modificare)

Leggi dai file e riporta: in quale/i pom è dichiarato `spring-boot-starter-data-jpa` (o altre dipendenze Hibernate/JPA), tutte le proprietà `spring.jpa.*` presenti negli `application.yml`/file di configurazione (con i valori), eventuali `@EnableJpaRepositories`, `@EntityScan`, entity, repository, test o classi che referenziano JPA. Se trovi codice o test che usano JPA, **fermati e chiedi**.

## Task

1. Sostituisci `spring-boot-starter-data-jpa` con `spring-boot-starter-jdbc` nel modulo in cui è dichiarato oggi (core, secondo gli standards: la dipendenza è ereditata dai sottosistemi). Se è dichiarato in più moduli, **fermati e chiedi**. Il driver PostgreSQL resta.
2. Rimuovi tutte le proprietà `spring.jpa.*` (incluse `hibernate.default_schema`, `ddl-auto`, `format_sql`, `show_sql`). **Mantieni** `spring.datasource.*` e le proprietà del pool Hikari invariate.
3. Rimuovi eventuali annotazioni/configurazioni JPA residue (`@EnableJpaRepositories`, `@EntityScan`, ecc.) se presenti solo come predisposizione, senza entità reali.

## Verifica richiesta

- `mvn clean install` dalla root con successo.
- Se l'ambiente è disponibile, avvia `servicessimulator-app`: l'applicazione parte, il `DataSource` si connette a PostgreSQL e il bean `JdbcTemplate` è disponibile (verifica nei log/contesto, senza aggiungere codice permanente). Riporta ciò che non hai potuto verificare.

## Al termine

Elenca file modificati e scelte prese per ambiguità.
