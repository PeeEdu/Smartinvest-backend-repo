package br.com.smartinvest.smart_invest_api.service;

import br.com.smartinvest.smart_invest_api.DTO.request.SimulacaoRequestDTO;
import br.com.smartinvest.smart_invest_api.DTO.response.SimulacaoResponseDTO;
import br.com.smartinvest.smart_invest_api.enums.TipoPerfil;
import br.com.smartinvest.smart_invest_api.mapper.SimulacaoMapper;
import br.com.smartinvest.smart_invest_api.model.RendaFixa;
import br.com.smartinvest.smart_invest_api.model.Simulacao;
import br.com.smartinvest.smart_invest_api.model.Usuario;
import br.com.smartinvest.smart_invest_api.repository.SimulacaoRepository;
import br.com.smartinvest.smart_invest_api.Util.CalculoRendaFixaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

@Service
@Slf4j
public class SimulacaoService {

    private final SimulacaoRepository simulacaoRepository;
    private final UsuarioService usuarioService;
    private final RendaFixaService rendaFixaService;

    public SimulacaoService(SimulacaoRepository simulacaoRepository, UsuarioService usuarioService, RendaFixaService rendaFixaService) {
        this.simulacaoRepository = simulacaoRepository;
        this.usuarioService = usuarioService;
        this.rendaFixaService = rendaFixaService;
    }

    public List<SimulacaoResponseDTO> getAllSimulacoes() {
        return simulacaoRepository.findAll().stream()
                .map(SimulacaoMapper::toSimulacaoResponseDTO)
                .collect(Collectors.toList());
    }

    public SimulacaoResponseDTO saveSimulacao(SimulacaoRequestDTO simulacaoRequestDTO) {
        Simulacao simulacao = SimulacaoMapper.toSimulacao(simulacaoRequestDTO);
        Usuario usuario = usuarioService.saveUsuario(simulacaoRequestDTO.tipoUsuario());
        RendaFixa rendaFixa = rendaFixaService.getRendaFixaById(simulacaoRequestDTO.idRendaFixa());

        if (usuario == null) {
            throw new RuntimeException("Usuario nao salvo");
        }

        simulacao.setUsuario(usuario);
        simulacao.setRendaFixa(rendaFixa);
        simulacao.setTipoPerfil(usuario != null ? TipoPerfil.values()[usuario.getTipo().ordinal()] : null);

        if (rendaFixa != null) {
            BigDecimal valorFinal = CalculoRendaFixaUtil.calcularValorFinal(
                    simulacao.getValorInicial(),
                    rendaFixa.getTaxaMensal(),
                    simulacao.getPrazoMeses()
            );
            simulacao.setValorFinal(valorFinal);
        } else {
            simulacao.setValorFinal(simulacao.getValorInicial()); // ou BigDecimal.ZERO
        }

        simulacaoRepository.save(simulacao);
        return SimulacaoMapper.toSimulacaoResponseDTO(simulacao);
    }

    public SimulacaoResponseDTO getByProtocolo(String protocolo) {
        Simulacao simulacao = simulacaoRepository.findByProtocolo(protocolo)
                .orElseThrow(() -> new RuntimeException("Simulação não encontrada"));

        return SimulacaoMapper.toSimulacaoResponseDTO(simulacao);
    }


}
