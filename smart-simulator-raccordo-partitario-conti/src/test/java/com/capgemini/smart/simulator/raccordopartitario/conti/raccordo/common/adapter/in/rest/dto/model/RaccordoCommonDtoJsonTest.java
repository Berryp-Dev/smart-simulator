package com.capgemini.smart.simulator.raccordopartitario.conti.raccordo.common.adapter.in.rest.dto.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

/**
 * Verifica che i nomi JSON dei campi sTy e sStatus coincidano con lo YAML
 * (senza una annotazione esplicita Jackson li chiamerebbe "sty" e "sstatus").
 */
class RaccordoCommonDtoJsonTest {

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Test
    void productAgreement_json_usesYamlFieldNames() throws Exception {
        ProdInfoAgreement dto = objectMapper.readValue("{\"ty\":\"RAG\",\"sTy\":\"CNV\",\"key\":\"K\"}", ProdInfoAgreement.class);

        assertEquals("CNV", dto.getSTy());
        JsonNode json = objectMapper.valueToTree(dto);
        assertTrue(json.has("sTy"));
        assertFalse(json.has("sty"));
    }

    @Test
    void statusInfo_json_usesYamlFieldNames() throws Exception {
        StatusInfoRP dto = objectMapper.readValue("{\"status\":\"ACTIVE\",\"sStatus\":\"SUB\"}", StatusInfoRP.class);

        assertEquals("SUB", dto.getSStatus());
        JsonNode json = objectMapper.valueToTree(dto);
        assertTrue(json.has("sStatus"));
        assertFalse(json.has("sstatus"));
    }
}
