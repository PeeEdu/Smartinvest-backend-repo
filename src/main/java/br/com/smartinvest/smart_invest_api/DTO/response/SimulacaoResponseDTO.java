package br.com.smartinvest.smart_invest_api.DTO.response;

import br.com.smartinvest.smart_invest_api.enums.TipoInvestimento;
import com.fasterxml.jackson.annotation.JsonFormat;
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
        BigDecimal taxaJuros,
        TipoInvestimento tipoInvestimento,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
        BigDecimal valorFinal,
        Date dataSimulacao
) {
}
