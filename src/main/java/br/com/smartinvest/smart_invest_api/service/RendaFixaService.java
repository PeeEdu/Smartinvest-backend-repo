package br.com.smartinvest.smart_invest_api.service;

import br.com.smartinvest.smart_invest_api.DTO.response.RendaFixaResponseDTO;
import br.com.smartinvest.smart_invest_api.mapper.RendaFixaMapper;
import br.com.smartinvest.smart_invest_api.model.RendaFixa;
import br.com.smartinvest.smart_invest_api.repository.RendaFixaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RendaFixaService {
    private final RendaFixaRepository rendaFixaRepository;

    public RendaFixaService(RendaFixaRepository rendaFixaRepository) {
        this.rendaFixaRepository = rendaFixaRepository;
    }

    public RendaFixa getRendaFixaById(Long id) {
        return rendaFixaRepository.findById(id).orElse(null);
    }

    public List<RendaFixa> getAllRendasFixas() {
        return rendaFixaRepository.findAll();
    }
}
