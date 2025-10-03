package br.com.smartinvest.smart_invest_api.model;

import br.com.smartinvest.smart_invest_api.model.Simulacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "tb_renda_variavel")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RendaVariavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //atributos de entrada da base
    @Column(name = "valor_aplicado", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorAplicado;
    @Column(name = "prazo_meses", nullable = false)
    private Integer prazoMeses;
    @Column(name = "data_simulacao", nullable = false)
    private LocalDate dataSimulacao = LocalDate.now();

    //atributos para regra de negócio e resultado da simulação
    @Column(name = "variacao_percentual_anual", precision = 5, scale = 2)// até 5 digitos e 2 decimais
    private BigDecimal variacaoPercentualAnual ;// Ex: A variação de 10.50% ao ano usada na simulação.

    @ElementCollection
    @CollectionTable(name = "tb_renda_variavel_variacoes", joinColumns =@JoinColumn(name = "renda_variavel_id"))
    @Column(name = "variacoes", precision = 5, scale = 2)
    private List<BigDecimal> variacoes;//Nível de risco/volatilidade simulado.
    @Column(name = "resultado_consolidado", precision = 19, scale = 2)//até 19 digitos e 2 decimais
    private BigDecimal resultadoConsolidado; // O valor final total após a simulação (Valor Aplicado + Rendimento).
    @Column(name = "ganho_liquido", precision = 19, scale = 2)
    private BigDecimal ganhoLiquido; // O resultado final (apenas o rendimento).


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "simulacao_id", unique = true, nullable = false)
    private Simulacao simulacao;

}
