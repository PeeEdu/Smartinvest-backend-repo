package br.com.smartinvest.smart_invest_api.model;



import br.com.smartinvest.smart_invest_api.enums.TipoInvestimento;
import br.com.smartinvest.smart_invest_api.enums.TipoUsuario;
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

    @Column(name = "protocolo")
    private String protocolo;

    // Relacionamento 1:1
    @OneToOne
    @JoinColumn(name = "idUsuario", unique = true) // garante que cada usuario tenha no máximo 1 simulação
    private Usuario usuario;

    //Criar Enum tipo investimento
    @Column(name = "tipoInvestimento")
    @Enumerated(EnumType.STRING)
    private TipoInvestimento tipo;


    @Column(name = "valorInicial")
    private BigDecimal valorInicial;

    @Column(name = "prazoMeses")
    private Integer prazoMeses;

    @Column(name = "taxaJuros")
    private Double taxaJuros;

    @Column(name = "valorFinal")
    private BigDecimal valorFinal;

    @Column(name = "dataSimulacao")
    private Date dataSimulacao;


}