package br.com.smartinvest.smart_invest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.smartinvest.smart_invest_api.model.RendaFixa;
import java.util.Optional;

@Repository
public interface RendaFixaRepository extends JpaRepository<RendaFixa, Long> {
    Optional<RendaFixa> findById(Long id); // já funciona normalmente
}

