package br.com.smartinvest.smart_invest_api.DTO.response;

import lombok.Builder;

import java.util.Date;

@Builder
public record RendaVariavelResponseDTO(
        Long id,
        String nome,
        String risco,
        Date dataCriacao
) {
}
