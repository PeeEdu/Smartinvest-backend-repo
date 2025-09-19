package br.com.smartinvest.smart_invest_api.DTO.response;

import br.com.smartinvest.smart_invest_api.enums.TipoUsuario;
import lombok.Builder;

import java.util.Date;

@Builder
public record UsuarioResponseDTO(
        Long idUsuario,
        Date dataCriacao,
        String nome,
        TipoUsuario tipo
) {
}
