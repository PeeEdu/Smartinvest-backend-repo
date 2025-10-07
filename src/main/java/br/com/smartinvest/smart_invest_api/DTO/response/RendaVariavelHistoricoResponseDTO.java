package br.com.smartinvest.smart_invest_api.DTO.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Builder
public record RendaVariavelHistoricoResponseDTO(
        Long acaoId,
        String nomeAcao,
        BigDecimal preco,
        Date dataAtualizacao
) {
}
