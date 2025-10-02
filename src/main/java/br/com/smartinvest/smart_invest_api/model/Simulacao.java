package br.com.smartinvest.smart_invest_api.model;

import br.com.smartinvest.smart_invest_api.enums.TipoInvestimento;
import br.com.smartinvest.smart_invest_api.enums.TipoPerfil;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "simulacao")
public class Simulacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSimulacao")
    private Long idSimulacao;

    @Column(name = "protocolo", nullable = false, unique = true)
    private String protocolo;

    // Relacionamento 1:1 (cada usuário pode ter no máximo uma simulação)
    @OneToOne
    @JoinColumn(name = "idUsuario", unique = true)
    private Usuario usuario;

    @Column(name = "tipoPerfil")
    @Enumerated(EnumType.STRING)
    private TipoPerfil tipoPerfil;

    @Column(name = "tipoInvestimento")
    @Enumerated(EnumType.STRING)
    private TipoInvestimento tipo;

    @Column(name = "valorInicial", nullable = false)
    private BigDecimal valorInicial;

    @Column(name = "prazoMeses", nullable = false)
    private Integer prazoMeses;

    @Column(name = "taxaJuros", nullable = false)
    private BigDecimal taxaJuros;

    @Column(name = "valorFinal")
    private BigDecimal valorFinal;

    @Column(name = "dataSimulacao", nullable = false)
    private Date dataSimulacao;
}
