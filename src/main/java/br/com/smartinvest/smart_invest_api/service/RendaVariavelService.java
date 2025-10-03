package br.com.smartinvest.smart_invest_api.service;

import br.com.smartinvest.smart_invest_api.DTO.response.RendaVariavelHistoricoResponseDTO;
import br.com.smartinvest.smart_invest_api.mapper.RendaVariavelHistoricoMapper;
import br.com.smartinvest.smart_invest_api.model.RendaVariavel;
import br.com.smartinvest.smart_invest_api.model.RendaVariavelHistorico;
import br.com.smartinvest.smart_invest_api.repository.RendaVariavelHistoricoRepository;
import br.com.smartinvest.smart_invest_api.repository.RendaVariavelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RendaVariavelService {

    private final RendaVariavelRepository rendaVariavelRepository;
    private final RendaVariavelHistoricoRepository historicoRepository;

    // Atualiza histórico com os preços atuais
    private void atualizarHistorico() {
        List<RendaVariavel> acoes = rendaVariavelRepository.findAll();
        for (RendaVariavel acao : acoes) {
            RendaVariavelHistorico historico = RendaVariavelHistorico.builder()
                    .acao(acao)
                    .preco(acao.getPrecoAtual())
                    .dataAtualizacao(acao.getDataAtualizacao())
                    .build();
            historicoRepository.save(historico);
        }
    }

    public List<RendaVariavelHistoricoResponseDTO> getHistoricoDTO() {
        atualizarHistorico(); // ⚡ Atualiza antes de retornar
        return historicoRepository.findAll().stream()
                .map(RendaVariavelHistoricoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<RendaVariavelHistoricoResponseDTO> getHistoricoPorAcaoDTO(Long acaoId) {
        atualizarHistorico(); // ⚡ Atualiza antes de retornar
        return historicoRepository.findAll().stream()
                .filter(h -> h.getAcao().getId().equals(acaoId))
                .map(RendaVariavelHistoricoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
