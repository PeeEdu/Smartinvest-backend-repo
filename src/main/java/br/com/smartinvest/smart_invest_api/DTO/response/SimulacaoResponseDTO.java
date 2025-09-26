package br.com.smartinvest.smart_invest_api.DTO.response;

import br.com.smartinvest.smart_invest_api.enums.TipoInvestimento;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Builder
public record SimulacaoResponseDTO(
        Long idSimulacao,
        String protocolo,
        BigDecimal valorInicial,
        Long idUsuario,
        String nomeUsuario,
        Integer prazoMeses,
        Double rentabilidadeEsperada,
        TipoInvestimento tipoInvestimento,
        BigDecimal valorFinal,
        Date dataSimulacao
) {
}
