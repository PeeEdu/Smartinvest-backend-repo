package br.com.smartinvest.smart_invest_api.mapper;

import br.com.smartinvest.smart_invest_api.DTO.request.SimulacaoRequestDTO;
import br.com.smartinvest.smart_invest_api.DTO.response.SimulacaoResponseDTO;
import br.com.smartinvest.smart_invest_api.model.Simulacao;
import br.com.smartinvest.smart_invest_api.util.RandomUtil;

import java.util.Date;

public class SimulacaoMapper {
    public static Simulacao toSimulacao(SimulacaoRequestDTO simulacaoRequestDTO) {
        return Simulacao.builder()
                .protocolo(RandomUtil.getRandomAlphaNumeric(15))
                .valorInicial(simulacaoRequestDTO.valorInicial())
                .tipo(simulacaoRequestDTO.tipoInvestimento())
                .taxaJuros(simulacaoRequestDTO.taxaJuros())
                .prazoMeses(simulacaoRequestDTO.prazoMeses())
                .dataSimulacao(new Date())
                .build();
    }


    public static SimulacaoResponseDTO toSimulacaoResponseDTO(Simulacao simulacao) {
        return SimulacaoResponseDTO.builder()
                .idSimulacao(simulacao.getIdSimulacao())
                .protocolo(simulacao.getProtocolo())
                .idUsuario(simulacao.getUsuario().getIdUsuario())
                .nomeUsuario(simulacao.getUsuario().getNome())
                .valorInicial(simulacao.getValorInicial())
                .tipoInvestimento(simulacao.getTipo())
                .taxaJuros(simulacao.getTaxaJuros())
                .prazoMeses(simulacao.getPrazoMeses())
                .valorFinal(simulacao.getValorFinal())
                .dataSimulacao(simulacao.getDataSimulacao())
                .build();
    }
}
