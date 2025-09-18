package br.com.smartinvest.smart_invest_api.model;

import br.com.smartinvest.smart_invest_api.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id //Ele seta a chave primaria
    @GeneratedValue // Ele gera um ID unico para cada usuário criado.
    private Long id;
    private String nome;
    private TipoUsuario tipo;

}

