package br.com.smartinvest.smart_invest_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "tb_renda_variavel")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RendaVariavelEntily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //atributos de entrada da base
    @Column(name = "valor_inicial", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorInicial;

    @Column(name = "prazo_meses", nullable = false)
    private Integer prazoMeses;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao = LocalDate.now();

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDate dataAtualizacao = LocalDate.now();

    @Column(name = "valor_final", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorFinal;

    @Column(name = "resultado_consolidado", nullable = false, precision = 19, scale = 2)
    private BigDecimal resultadoConsolidado;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "simulacao_id", unique = true, nullable = false)
    private Simulacao simulacao;
    
}

