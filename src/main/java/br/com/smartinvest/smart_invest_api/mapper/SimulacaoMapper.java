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
                .rentabilidadeEsperada(simulacaoRequestDTO.rentabilidadeEsperada())
                .dataSimulacao(new Date())
                .build();
    }


    public static SimulacaoResponseDTO toSimulacaoResponseDTO(Simulacao simulacao) {
        return SimulacaoResponseDTO.builder()
                .idSimulacao(simulacao.getIdSimulacao())
                .protocolo(simulacao.getProtocolo())
                .idUsuario(simulacao.getUsuario().getIdUsuario())
                .valorInicial(simulacao.getValorInicial())
                .tipoInvestimento(simulacao.getTipo())
                .rentabilidadeEsperada(simulacao.getRentabilidadeEsperada())
                .valorFinal(simulacao.getValorFinal())
                .dataSimulacao(simulacao.getDataSimulacao())
                .build();
    }
}
