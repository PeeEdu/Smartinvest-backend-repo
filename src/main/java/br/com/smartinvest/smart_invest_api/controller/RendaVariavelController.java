package br.com.smartinvest.smart_invest_api.controller;

import br.com.smartinvest.smart_invest_api.DTO.response.RendaVariavelHistoricoResponseDTO;
import br.com.smartinvest.smart_invest_api.model.RendaVariavelHistorico;
import br.com.smartinvest.smart_invest_api.repository.RendaVariavelHistoricoRepository;
import br.com.smartinvest.smart_invest_api.service.RendaVariavelService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acoes")
@RequiredArgsConstructor
public class RendaVariavelController {

    private final RendaVariavelService rendaVariavelService;

    @GetMapping("/historico")
    @Operation(summary = "Listar Historico")
    public List<RendaVariavelHistoricoResponseDTO> getHistorico() {
        return rendaVariavelService.getHistoricoDTO();
    }

    @GetMapping("/{id}/historico")
    @Operation(summary = "Listar Historico por Ação")
    public List<RendaVariavelHistoricoResponseDTO> getHistoricoPorAcao(@PathVariable Long id) {
        return rendaVariavelService.getHistoricoPorAcaoDTO(id);
    }
}
