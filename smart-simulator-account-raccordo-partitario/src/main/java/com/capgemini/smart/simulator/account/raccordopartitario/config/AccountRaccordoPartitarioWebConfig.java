package com.capgemini.smart.simulator.account.raccordopartitario.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Wiring web del modulo: registra l'interceptor che fissa la lingua dei
 * messaggi di bean validation per le sole Resource del modulo.
 */
@Configuration
public class AccountRaccordoPartitarioWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccountRaccordoPartitarioLocaleInterceptor());
    }
}
