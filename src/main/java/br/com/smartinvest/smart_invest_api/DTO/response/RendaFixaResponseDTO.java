package br.com.smartinvest.smart_invest_api.DTO.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Builder
public record RendaFixaResponseDTO(
        Long id,
        String nome,
        BigDecimal taxaMensal,
        String risco,
        Date dataCriacao
) {
}
