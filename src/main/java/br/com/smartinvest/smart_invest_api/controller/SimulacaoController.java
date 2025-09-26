package br.com.smartinvest.smart_invest_api.controller;


import br.com.smartinvest.smart_invest_api.DTO.response.BaseResponse;
import br.com.smartinvest.smart_invest_api.service.SimulacaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulacao")
public class SimulacaoController {
    private final SimulacaoService simulacaoService;

    public SimulacaoController(SimulacaoService simulacaoService) {
        this.simulacaoService = simulacaoService;
    }

    @GetMapping
    @Operation(summary = "Listar Simulações")
    public ResponseEntity<BaseResponse> getSimulcao() {
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.builder()
                .message("Simulaçõe Listadas")
                .status(HttpStatus.OK)
                .data(simulacaoService.getAllSimulacoes())
                .build()
        );
    }

    @PostMapping
    @Operation(summary = "Salvar Simulações")
    public ResponseEntity<BaseResponse> saveSimulacao(@RequestBody SimulacaoRequestDTO simulacaoRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.builder()
                .message("Simulaçõe Salva")
                .status(HttpStatus.OK)
                .data(simulacaoService.saveSimulacao(simulacaoRequestDTO))
                .build()
        );

    }



}
