package br.com.smartinvest.smart_invest_api.model;

import br.com.smartinvest.smart_invest_api.enums.TipoUsuario;
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
    @Id //Ele seta a chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario") // <-- Especifica o nome exato da coluna no banco
    private Long idUsuario;

    @Column(name = "dataCriacao")
    private Date dataCriacao;


    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

}

