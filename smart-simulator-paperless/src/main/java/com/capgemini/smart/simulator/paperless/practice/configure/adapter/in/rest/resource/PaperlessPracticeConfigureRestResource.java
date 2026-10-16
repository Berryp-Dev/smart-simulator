package com.capgemini.smart.simulator.paperless.practice.configure.adapter.in.rest.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.smart.simulator.core.rest.request.RestRequest;
import com.capgemini.smart.simulator.paperless.practice.configure.adapter.in.rest.dto.PaperlessPracticeConfigureDtoReq;
import com.capgemini.smart.simulator.paperless.practice.configure.adapter.in.rest.dto.PaperlessPracticeConfigureDtoResp;
import com.capgemini.smart.simulator.paperless.practice.configure.adapter.in.rest.mapper.PaperlessPracticeConfigureMapper;
import com.capgemini.smart.simulator.paperless.practice.configure.application.port.in.ConfigurePaperlessPracticeUseCase;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.model.PaperlessPracticeConfiguration;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.model.PaperlessPracticeConfigureResult;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Entry point REST dello scenario Practice Configure.
 */
@RestController
@Tag(name = "Practice")
@RequestMapping("/paperless/practice")
public class PaperlessPracticeConfigureRestResource {

    private final ConfigurePaperlessPracticeUseCase configurePaperlessPracticeUseCase;

    public PaperlessPracticeConfigureRestResource(ConfigurePaperlessPracticeUseCase configurePaperlessPracticeUseCase) {
        this.configurePaperlessPracticeUseCase = configurePaperlessPracticeUseCase;
    }

    @PostMapping("/configure")
    public ResponseEntity<PaperlessPracticeConfigureDtoResp> configure(@RequestBody RestRequest<PaperlessPracticeConfigureDtoReq> request) {
        PaperlessPracticeConfiguration practiceConfiguration = PaperlessPracticeConfigureMapper.mapToDomain(request);
        PaperlessPracticeConfigureResult result = configurePaperlessPracticeUseCase.configurePractice(practiceConfiguration);
        return ResponseEntity.ok(PaperlessPracticeConfigureMapper.mapToDtoResp(result));
    }
}
