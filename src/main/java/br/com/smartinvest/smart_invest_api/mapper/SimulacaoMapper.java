package br.com.smartinvest.smart_invest_api.mapper;

import br.com.smartinvest.smart_invest_api.DTO.request.SimulacaoRequestDTO;
import br.com.smartinvest.smart_invest_api.DTO.response.SimulacaoResponseDTO;
import br.com.smartinvest.smart_invest_api.model.Simulacao;
import br.com.smartinvest.smart_invest_api.util.RandomUtil;

import java.math.BigDecimal;
import java.util.Date;

public class SimulacaoMapper {
    public static Simulacao toSimulacao(SimulacaoRequestDTO simulacaoRequestDTO) {
        return Simulacao.builder()
                .protocolo(RandomUtil.getRandomAlphaNumeric(15))
                .valorInicial(simulacaoRequestDTO.valorInicial())
                .tipo(simulacaoRequestDTO.tipoInvestimento())
                .prazoMeses(simulacaoRequestDTO.prazoMeses())
                .dataSimulacao(new Date())
                .build();
    }


    public static SimulacaoResponseDTO toSimulacaoResponseDTO(Simulacao simulacao) {
        if (simulacao == null) {
            return null;
        }

        // Pega os dados do investimento apenas se houver
        String nomeInvestimento = null;
        if (simulacao.getRendaFixa() != null) {
            nomeInvestimento = simulacao.getRendaFixa().getNome();
        }

        return SimulacaoResponseDTO.builder()
                .idSimulacao(simulacao.getIdSimulacao())
                .protocolo(simulacao.getProtocolo())
                .idUsuario(simulacao.getUsuario() != null ? simulacao.getUsuario().getIdUsuario() : null)
                .nomeUsuario(simulacao.getUsuario() != null ? simulacao.getUsuario().getNome() : null)
                .tipoUsuario(simulacao.getUsuario() != null ? simulacao.getUsuario().getTipo() : null)
                .tipoPerfil(simulacao.getTipoPerfil())
                .tipoInvestimento(simulacao.getTipo())
                .valorInicial(simulacao.getValorInicial())
                .prazoMeses(simulacao.getPrazoMeses())
                .valorFinal(simulacao.getValorFinal())
                .rendaFixa(simulacao.getRendaFixa())
                .dataSimulacao(simulacao.getDataSimulacao())
                .build();
    }
}
