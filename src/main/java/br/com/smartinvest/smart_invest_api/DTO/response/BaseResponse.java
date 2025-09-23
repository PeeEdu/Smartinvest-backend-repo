package br.com.smartinvest.smart_invest_api.DTO.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record BaseResponse(
        String message,
        HttpStatus status,
        Object data
) {
}
