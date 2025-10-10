package br.com.smartinvest.smart_invest_api.DTO.response;

import br.com.smartinvest.smart_invest_api.enums.TipoInvestimento;
import br.com.smartinvest.smart_invest_api.enums.TipoPerfil;
import br.com.smartinvest.smart_invest_api.enums.TipoUsuario;
import br.com.smartinvest.smart_invest_api.model.RendaFixa;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Builder
public record SimulacaoResponseDTO(
        Long idSimulacao,
        String protocolo,
        Long idUsuario,
        String nomeUsuario,
        TipoUsuario tipoUsuario,
        TipoPerfil tipoPerfil,
        TipoInvestimento tipoInvestimento,
        BigDecimal valorInicial,
        RendaFixa rendaFixa,
        Integer prazoMeses,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
        BigDecimal valorFinal,
        Date dataSimulacao
) {}
