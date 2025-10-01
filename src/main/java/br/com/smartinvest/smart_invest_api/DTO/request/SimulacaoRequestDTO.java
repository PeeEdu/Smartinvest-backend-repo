package br.com.smartinvest.smart_invest_api.DTO.request;

import br.com.smartinvest.smart_invest_api.enums.TipoInvestimento;
import br.com.smartinvest.smart_invest_api.enums.TipoUsuario;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SimulacaoRequestDTO(
        BigDecimal valorInicial,
        TipoInvestimento tipoInvestimento,
        BigDecimal taxaJuros,
        Integer prazoMeses,
        TipoUsuario tipoUsuario
) {
}
