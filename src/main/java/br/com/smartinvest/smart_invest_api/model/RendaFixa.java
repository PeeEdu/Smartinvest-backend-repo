package br.com.smartinvest.smart_invest_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "renda_fixa")
public class RendaFixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_renda_fixa")
    private Long id;

    @Column(name = "valor_inicial", nullable = false)
    private BigDecimal valorInicial;

    @Column(name = "prazo_meses", nullable = false)
    private Integer prazoMeses;

    @Column(name = "taxa_juros", nullable = false)
    private BigDecimal taxaJuros;

    @Column(name = "valor_final")
    private BigDecimal valorFinal;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }
}