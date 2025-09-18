package br.com.smartinvest.smart_invest_api.DTO.response;

import br.com.smartinvest.smart_invest_api.enums.TipoUsuario;
import lombok.Builder;

@Builder
public record UsuarioResponseDTO(
        Long id,
        String nome,
        TipoUsuario tipo
) {
}
