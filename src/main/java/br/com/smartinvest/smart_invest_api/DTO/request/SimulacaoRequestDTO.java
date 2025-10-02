package br.com.smartinvest.smart_invest_api.DTO.request;

import br.com.smartinvest.smart_invest_api.enums.TipoInvestimento;
import br.com.smartinvest.smart_invest_api.enums.TipoUsuario;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SimulacaoRequestDTO(
        @NotNull
        BigDecimal valorInicial,

        @NotNull
        TipoInvestimento tipoInvestimento,

        @NotNull
        Double taxaJuros,

        @NotNull
        TipoUsuario tipoUsuario
) {
}