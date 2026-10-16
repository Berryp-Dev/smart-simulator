package com.capgemini.smart.simulator.config;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Builder;

/**
 * Configurazione OpenAPI/Swagger per SmartCase Template
 * Pattern conforme a Hermes - configurazione diretta senza ereditarietà
 */
@Configuration
public class OpenApiConfiguration {

  @Value("${server.servlet.context-path:}")
  private String contextPath;

  @Bean
  @Primary
  public OpenAPI customOpenAPI() {
    Contact contact = new Contact();
    contact.setName("SmartCase Team");
    contact.setEmail("smartcase-team@capgemini.com");

    // Build URL based on context path
    String basePath = contextPath.isEmpty() ? "" : contextPath;

    // Use a relative server that automatically adapts to the protocol and host of the request.
    Server server = new Server();
    server.setUrl(basePath.isEmpty() ? "/" : basePath);
    server.setDescription("Current server (auto-detected protocol and host)");

    return new OpenAPI()
      .servers(List.of(server))
      .info(new Info()
        .title("SmartSimulator :: App")
        .description("Unico modulo avviabile del progetto: espone in un solo processo tutti\r\n"
        		+ "        i sottosistemi simulati (core + ogni modulo sottosistema).")
        .summary("Simulatore di sottosistemi esterni")
        .version("1.1.0")
        .contact(contact)
        .license(new License()
          .name("Capgemini")
          .url("http://www.capgemini.com"))
        .extensions(createExtensions()));
  }

  private static final String CUSTOM_EXTENSION_NAME = "x-change-log";

  private static Map < String, Object > createExtensions() {

    Map < String, Object > result = new HashMap < > ();

    List < Changelog > changelogs = List.of(
    		release_1_1_0(),
    		release_1_0_0()
    		);

    result.put(CUSTOM_EXTENSION_NAME, changelogs);

    return result;
  }

  @Builder(setterPrefix = "with")
  private record Changelog(
    String version,
    LocalDate date,
    ChangeLogDetail changes) {}

  @Builder(setterPrefix = "with")
  private record ChangeLogDetail(
    ChangeSection endpoints,
    ChangeSection schemas,
    ChangeSection enums) {}

  @Builder(setterPrefix = "with")
  private record ChangeSection(
    Map < String, List < String >> added,
    Map < String, List < String >> modified,
    Map < String, List < String >> removed) {}

  private static class Version {

    private static final String VERSION_DELIMITER = ".";
    private static final String SNAPSHOT_APPEND_VALUE = "-SNAPSHOT";

    public static String of (int major, int minor, int hotfix) {
      return of(major, minor, hotfix, false);
    }

    public static String of (int major, int minor, int hotfix, boolean snapshot) {

      StringJoiner joiner = new StringJoiner(VERSION_DELIMITER);

      joiner.add(String.valueOf(major))
        .add(String.valueOf(minor))
        .add(String.valueOf(hotfix));

      String version = joiner.toString();

      if (snapshot) {
        version = version.concat(SNAPSHOT_APPEND_VALUE);
      }

      return version;
    }
  }

  // =========================================================================
  // CHANGELOG RELEASES
  // =========================================================================
  private static Changelog release_1_1_0() {

	  return Changelog.builder().withVersion(Version.of(1, 1, 0)).withDate(LocalDate.of(2026, 10, 16)).withChanges(
	  ChangeLogDetail.builder()

	  .withSchemas(
	  ChangeSection.builder()

	  .withAdded(Map.of("BaseRequest", List.of("class created for Hermes simulation"),

	  "Context", List.of("class created for Hermes simulation"),

	  "DefaultContext", List.of("class created for Hermes simulation"),

	  "DefaultRestRequest", List.of("class created for Hermes simulation"),

	  "HermesHeaderDto", List.of("class created for Hermes simulation"),

	  "RestRequest", List.of("class created for Hermes simulation")))

	  .withModified(Map.of("PaperlessDocumentSignatureDtoReq", List.of("converted from record to class"),

	  "PaperlessDocumentSignatureMapper", List.of("removed mapper and adapted to Hermes style"),

	  "PaperlessDocumentSignatureRestResource", List.of("renamed PaperlessDocumentSignatureResource > PaperlessDocumentSignatureRestResource", "implemented Hermes-style request"),

	  "PaperlessPracticeConfigureDtoReq", List.of("converted from record to class"),

	  "PaperlessPracticeConfigureMapper", List.of("removed explicit scenarioCode mapping"),

	  "PaperlessPracticeConfigureRestResource", List.of("renamed PaperlessPracticeConfigureResource > PaperlessPracticeConfigureRestResource", "implemented Hermes-style request"))).build())

	  .build()).build();
	}
  
  private static Changelog release_1_0_0() {

	  return Changelog.builder().withVersion(Version.of(1, 0, 0)).withDate(LocalDate.of(2026, 10, 10)).withChanges(
	  ChangeLogDetail.builder().build()).build();
	}
}