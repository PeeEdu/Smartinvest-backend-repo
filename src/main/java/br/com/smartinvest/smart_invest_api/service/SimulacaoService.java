package br.com.smartinvest.smart_invest_api.service;

import br.com.smartinvest.smart_invest_api.DTO.request.SimulacaoRequestDTO;
import br.com.smartinvest.smart_invest_api.DTO.response.SimulacaoResponseDTO;
import br.com.smartinvest.smart_invest_api.mapper.SimulacaoMapper;
import br.com.smartinvest.smart_invest_api.mapper.UsuarioMapper;
import br.com.smartinvest.smart_invest_api.model.Simulacao;
import br.com.smartinvest.smart_invest_api.model.Usuario;
import br.com.smartinvest.smart_invest_api.repository.SimulacaoRepository;
import br.com.smartinvest.smart_invest_api.repository.UsuarioRepository;
import br.com.smartinvest.smart_invest_api.enums.TipoUsuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

@Service
@Slf4j
public class SimulacaoService {

    private final SimulacaoRepository simulacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public SimulacaoService(SimulacaoRepository simulacaoRepository, UsuarioRepository usuarioRepository) {
        this.simulacaoRepository = simulacaoRepository;
        this.usuarioRepository = usuarioRepository;
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
        simulacao.setRentabilidadeEsperada(simulacaoRequestDTO.rentabilidadeEsperada());
        return SimulacaoMapper.toSimulacaoResponseDTO(simulacaoRepository.save(simulacao));
    }

    public SimulacaoResponseDTO saveSimulacao(SimulacaoRequestDTO simulacaoRequestDTO) {
        // Criar Simulação
        Simulacao simulacao = SimulacaoMapper.toSimulacao(simulacaoRequestDTO);

        // Criar usuário automaticamente (com tipo default INICIANTE)
        Usuario usuario = Usuario.builder()
                .nome(UsuarioMapper.toUsuario(new br.com.smartinvest.smart_invest_api.DTO.request.UsuarioRequestDTO(TipoUsuario.INICIANTE)).getNome())
                .tipo(TipoUsuario.INICIANTE)
                .dataCriacao(new Date())
                .simulacao(simulacao) // vincula a simulação ao usuário
                .build();

        // Vincula usuário à simulação (relacionamento bidirecional)
        simulacao.setUsuario(usuario);

        // Salva primeiro a simulação (cascade ALL vai salvar o usuário)
        simulacaoRepository.save(simulacao);

        return SimulacaoMapper.toSimulacaoResponseDTO(simulacao);
    }
}
