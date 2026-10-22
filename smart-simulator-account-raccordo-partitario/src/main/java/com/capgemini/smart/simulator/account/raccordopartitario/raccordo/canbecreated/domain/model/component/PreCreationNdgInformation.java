package com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.model.component;

import com.capgemini.smart.simulator.account.raccordopartitario.raccordo.canbecreated.domain.enums.NdgNatureCode;
import java.util.List;

/**
 * Modello di dominio (value object annidato) corrispondente allo schema YAML {@code SimpleProductNdgInfo}.
 * Separato dal DTO REST: nessuna dipendenza da framework.
 */
public record PreCreationNdgInformation(
        String cntrNdgKey,
        String cntrNdgType,
        List<String> cntrSignNdgKeys,
        NdgNatureCode ndgNature) {
}
