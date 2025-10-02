package br.com.smartinvest.smart_invest_api.service;

import br.com.smartinvest.smart_invest_api.DTO.request.SimulacaoRequestDTO;
import br.com.smartinvest.smart_invest_api.DTO.response.SimulacaoResponseDTO;
import br.com.smartinvest.smart_invest_api.DTO.response.UsuarioResponseDTO;
import br.com.smartinvest.smart_invest_api.enums.TipoPerfil;
import br.com.smartinvest.smart_invest_api.mapper.SimulacaoMapper;
import br.com.smartinvest.smart_invest_api.mapper.UsuarioMapper;
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

    public SimulacaoService(SimulacaoRepository simulacaoRepository, UsuarioService usuarioService) {
        this.simulacaoRepository = simulacaoRepository;
        this.usuarioService = usuarioService;
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
        simulacao.setTaxaJuros(simulacaoRequestDTO.taxaJuros());
        simulacao.setPrazoMeses(simulacaoRequestDTO.prazoMeses());
        return SimulacaoMapper.toSimulacaoResponseDTO(simulacaoRepository.save(simulacao));
    }

    public SimulacaoResponseDTO saveSimulacao(SimulacaoRequestDTO simulacaoRequestDTO) {
        Simulacao simulacao = SimulacaoMapper.toSimulacao(simulacaoRequestDTO);
        Usuario usuario = usuarioService.saveUsuario(simulacaoRequestDTO.tipoUsuario());

        if (usuario == null) {
            throw new RuntimeException("Usuario nao salvo");
        }

        simulacao.setUsuario(usuario);

        // Definir perfil de acordo com o tipo de usuário
        switch (usuario.getTipo()) {
            case INICIANTE -> simulacao.setTipoPerfil(TipoPerfil.CONSERVADOR);
            case INTERMEDIARIO -> simulacao.setTipoPerfil(TipoPerfil.MODERADO);
            case AVANCADO -> simulacao.setTipoPerfil(TipoPerfil.ARROJADO);
        }

        // Calcula o valor final usando o utilitário
        BigDecimal valorFinal = CalculoRendaFixaUtil.calcularValorFinal(
                simulacao.getValorInicial(),
                simulacao.getTaxaJuros(),
                simulacao.getPrazoMeses()
        );
        simulacao.setValorFinal(valorFinal);

        simulacaoRepository.save(simulacao);
        return SimulacaoMapper.toSimulacaoResponseDTO(simulacao);
    }


}
