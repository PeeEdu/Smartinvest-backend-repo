package br.com.smartinvest.smart_invest_api.DTO.request;

import br.com.smartinvest.smart_invest_api.enums.TipoUsuario;
import lombok.Builder;

@Builder
public record UsuarioRequestDTO(
        String nome,
        TipoUsuario tipo
) {
}
