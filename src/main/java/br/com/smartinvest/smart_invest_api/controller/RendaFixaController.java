package br.com.smartinvest.smart_invest_api.controller;

import br.com.smartinvest.smart_invest_api.DTO.response.BaseResponse;
import br.com.smartinvest.smart_invest_api.mapper.RendaFixaMapper;
import br.com.smartinvest.smart_invest_api.service.RendaFixaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/renda-fixa")
@RequiredArgsConstructor
public class RendaFixaController {

    private final RendaFixaService rendaFixaService;

    @GetMapping
    @Operation(summary = "Lista as Rendas Fixas")
    public ResponseEntity<BaseResponse> getAllRendasFixas() {
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.builder()
                .message("Rendas Fixas Listadas")
                .status(HttpStatus.OK)
                .data(RendaFixaMapper.toRendaFixaResponseDTO(rendaFixaService.getAllRendasFixas()))
                .build()
        );
    }
}
