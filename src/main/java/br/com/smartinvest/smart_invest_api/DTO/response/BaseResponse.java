package br.com.smartinvest.smart_invest_api.DTO.response;

import lombok.Builder;

@Builder
public record BaseResponse(
        String message,
        String status,
        Object data
) {
}
