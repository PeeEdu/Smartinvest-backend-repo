package br.com.smartinvest.smart_invest_api.repository;

import br.com.smartinvest.smart_invest_api.model.Simulacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SimulacaoRepository extends JpaRepository<Simulacao, Long> {
    Optional<Simulacao> findByProtocolo(String protocolo);
}
