package br.com.smartinvest.smart_invest_api.model;

import br.com.smartinvest.smart_invest_api.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "usuario")
public class Usuario {

    // Identificador
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long idUsuario;

    // Metadados
    @Column(name = "dataCriacao")
    private Date dataCriacao;

    // Atributos principais
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    // Relacionamentos
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private Simulacao simulacao;
}