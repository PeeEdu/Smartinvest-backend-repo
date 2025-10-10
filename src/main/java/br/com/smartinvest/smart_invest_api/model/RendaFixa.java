package br.com.smartinvest.smart_invest_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "rendaFixa")
public class RendaFixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal taxaMensal;

    private String risco;

    @Column(name = "dataCriacao", nullable = false)
    private Date dataCriacao;
}
