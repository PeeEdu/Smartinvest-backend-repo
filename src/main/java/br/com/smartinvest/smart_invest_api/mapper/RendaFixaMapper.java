package br.com.smartinvest.smart_invest_api.mapper;

import br.com.smartinvest.smart_invest_api.DTO.response.RendaFixaResponseDTO;
import br.com.smartinvest.smart_invest_api.model.RendaFixa;

public class RendaFixaMapper {
    public static RendaFixaResponseDTO toRendaFixaResponseDTO(RendaFixa rendaFixa) {
        return RendaFixaResponseDTO.builder()
                .id(rendaFixa.getId())
                .nome(rendaFixa.getNome())
                .taxaMensal(rendaFixa.getTaxaMensal())
                .dataCriacao(rendaFixa.getDataCriacao())
                .build();
    }
}
