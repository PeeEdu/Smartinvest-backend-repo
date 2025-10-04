package br.com.smartinvest.smart_invest_api.repository;

import br.com.smartinvest.smart_invest_api.model.RendaFixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendaFixaRepository  extends JpaRepository<RendaFixa, Long> {
}
