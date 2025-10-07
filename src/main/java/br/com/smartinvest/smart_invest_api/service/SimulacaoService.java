package br.com.smartinvest.smart_invest_api.service;

import br.com.smartinvest.smart_invest_api.DTO.request.SimulacaoRequestDTO;
import br.com.smartinvest.smart_invest_api.DTO.response.SimulacaoResponseDTO;
import br.com.smartinvest.smart_invest_api.DTO.response.UsuarioResponseDTO;
import br.com.smartinvest.smart_invest_api.enums.TipoPerfil;
import br.com.smartinvest.smart_invest_api.mapper.SimulacaoMapper;
import br.com.smartinvest.smart_invest_api.mapper.UsuarioMapper;
import br.com.smartinvest.smart_invest_api.model.RendaFixa;
import br.com.smartinvest.smart_invest_api.model.Simulacao;
import br.com.smartinvest.smart_invest_api.model.Usuario;
import br.com.smartinvest.smart_invest_api.repository.SimulacaoRepository;
import br.com.smartinvest.smart_invest_api.repository.UsuarioRepository;
import br.com.smartinvest.smart_invest_api.enums.TipoUsuario;
import br.com.smartinvest.smart_invest_api.util.CalculoRendaFixaUtil;
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

    public SimulacaoResponseDTO getSimulacaoById(Long id) {
        return SimulacaoMapper.toSimulacaoResponseDTO(simulacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Simulação não encontrada")));
    }

    public SimulacaoResponseDTO updateSimulacao(SimulacaoRequestDTO simulacaoRequestDTO, Long id) {
        Simulacao simulacao = simulacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Simulação não encontrada"));
        simulacao.setValorInicial(simulacaoRequestDTO.valorInicial());
        simulacao.setTipo(simulacaoRequestDTO.tipoInvestimento());
        simulacao.setPrazoMeses(simulacaoRequestDTO.prazoMeses());
        return SimulacaoMapper.toSimulacaoResponseDTO(simulacaoRepository.save(simulacao));
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

        // Calcula o valor final usando o utilitário somente se Renda Fixa existir
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
}
