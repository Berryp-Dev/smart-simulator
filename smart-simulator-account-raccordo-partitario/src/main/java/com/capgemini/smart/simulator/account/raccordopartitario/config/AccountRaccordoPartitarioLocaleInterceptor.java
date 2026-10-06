package com.capgemini.smart.simulator.account.raccordopartitario.config;

import com.capgemini.smart.simulator.account.raccordopartitario.currentaccount.configure.adapter.in.rest.resource.AccountRaccordoPartitarioCurrentAccountConfigureResource;
import com.capgemini.smart.simulator.account.raccordopartitario.sales.adapter.in.rest.resource.AccountRaccordoPartitarioSalesResource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;
import java.util.Locale;

/**
 * Fissa la lingua inglese per i messaggi di bean validation, solo per le
 * richieste gestite dalle Resource di questo modulo (riconosciute dal tipo
 * del handler, non dal path). Il locale e' ripristinato da Spring a fine
 * richiesta; Paperless, springdoc e gli altri endpoint non sono toccati.
 */
public class AccountRaccordoPartitarioLocaleInterceptor implements HandlerInterceptor {

    private static final List<Class<?>> MODULE_RESOURCES = List.of(
            AccountRaccordoPartitarioSalesResource.class,
            AccountRaccordoPartitarioCurrentAccountConfigureResource.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod handlerMethod && isModuleResource(handlerMethod.getBeanType())) {
            LocaleContextHolder.setLocale(Locale.ENGLISH);
        }
        return true;
    }

    private boolean isModuleResource(Class<?> beanType) {
        return MODULE_RESOURCES.stream().anyMatch(resource -> resource.isAssignableFrom(beanType));
    }
}
