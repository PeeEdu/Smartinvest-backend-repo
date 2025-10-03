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
@Table(name = "rendaVariavel")
public class RendaVariavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo; // Ação, FII, ETF, etc.

    @Column(nullable = false)
    private BigDecimal precoAtual;

    @Builder.Default
    private String volatilidade = "Alta";

    private String setor;

    @Column(name = "dataAtualizacao", nullable = false)
    @Builder.Default
    private Date dataAtualizacao = new Date();
}
