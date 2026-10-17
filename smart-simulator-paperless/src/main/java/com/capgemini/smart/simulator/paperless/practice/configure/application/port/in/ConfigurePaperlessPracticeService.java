package com.capgemini.smart.simulator.paperless.practice.configure.application.port.in;

import com.capgemini.smart.simulator.paperless.practice.configure.domain.model.PaperlessPracticeConfiguration;
import com.capgemini.smart.simulator.paperless.practice.configure.domain.model.PaperlessPracticeConfigureResult;

/**
 * Driving port dello scenario Practice Configure.
 */
public interface ConfigurePaperlessPracticeService {

    PaperlessPracticeConfigureResult configurePractice(PaperlessPracticeConfiguration practiceConfigure);
}
