package br.com.smartinvest.smart_invest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendaVariavelRepository extends JpaRepository<RendaVariavel, Long> {
}
