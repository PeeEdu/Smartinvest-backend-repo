package br.com.smartinvest.smart_invest_api.mapper;

import br.com.smartinvest.smart_invest_api.DTO.response.RendaVariavelHistoricoResponseDTO;
import br.com.smartinvest.smart_invest_api.model.RendaVariavelHistorico;

public class RendaVariavelHistoricoMapper {

    public static RendaVariavelHistoricoResponseDTO toDTO(RendaVariavelHistorico historico) {
        return RendaVariavelHistoricoResponseDTO.builder()
                .acaoId(historico.getAcao().getId())
                .nomeAcao(historico.getAcao().getNome())
                .preco(historico.getPreco())
                .dataAtualizacao(historico.getDataAtualizacao())
                .build();
    }
}
