package br.com.smartinvest.smart_invest_api.DTO.request;

import br.com.smartinvest.smart_invest_api.enums.TipoInvestimento;
import br.com.smartinvest.smart_invest_api.enums.TipoPerfil;
import br.com.smartinvest.smart_invest_api.enums.TipoUsuario;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record SimulacaoRequestDTO(
        TipoUsuario tipoUsuario,
        TipoPerfil tipoPerfil,
        TipoInvestimento tipoInvestimento,
        BigDecimal valorInicial,
        BigDecimal taxaJuros,
        Integer prazoMeses
) {
}
