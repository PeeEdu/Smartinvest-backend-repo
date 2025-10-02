package br.com.smartinvest.smart_invest_api.controller;


import br.com.smartinvest.smart_invest_api.DTO.request.SimulacaoRequestDTO;
import br.com.smartinvest.smart_invest_api.DTO.response.BaseResponse;
import br.com.smartinvest.smart_invest_api.DTO.response.SimulacaoResponseDTO;
import br.com.smartinvest.smart_invest_api.exceptions.RequestIsNullException;
import br.com.smartinvest.smart_invest_api.service.SimulacaoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/simulacao")
public class SimulacaoController {
    private final SimulacaoService simulacaoService;
    private Object simulacaoDTO;

    public SimulacaoController(SimulacaoService simulacaoService) {
        this.simulacaoService = simulacaoService;
    }

    @GetMapping
    @Operation(summary = "Listar Simulações")
    public ResponseEntity<BaseResponse> getAllSimulcao() {
        List<SimulacaoResponseDTO> simulacaoResponseDTO = simulacaoService.getAllSimulacoes();

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.builder()
                .message("Simulaçõe Listadas")
                .status(HttpStatus.OK)
                .data(simulacaoResponseDTO)
                .build()
        );
    }

    @PostMapping
    @Operation(summary = "Salvar Simulações")
    public ResponseEntity<BaseResponse> saveSimulacao(@RequestBody @Valid SimulacaoRequestDTO simulacaoRequestDTO) {

        if (simulacaoRequestDTO == null) {
            throw new RequestIsNullException("SimulacaoRequestDTO");
        }

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.builder()
                .message("Simulaçõe Salva")
                .status(HttpStatus.OK)
                .data(simulacaoService.saveSimulacao(simulacaoRequestDTO))
                .build()
        );
    }

    @GetMapping("/{protocolo}")
    @Operation(summary = "Recuperar simulação por protocolo")
    public ResponseEntity<BaseResponse> getSimulacaoByProtocolo(@PathVariable String protocolo) {
        SimulacaoResponseDTO simulacaoDTO = simulacaoService.buscarPorProtocolo(protocolo);

        // Tratamento para caso a simulação não seja encontrada
        if (simulacaoDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.builder()
                    .message("Simulação não encontrada para o protocolo: " + protocolo)
                    .status(HttpStatus.NOT_FOUND)
                    .data(null)
                    .build());
        }

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.builder()
                .message("Simulação Encontrada!")
                .status(HttpStatus.OK)
                .data(simulacaoDTO)
                .build());
    }
}