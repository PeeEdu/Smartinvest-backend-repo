package br.com.smartinvest.smart_invest_api.mapper;

import br.com.smartinvest.smart_invest_api.DTO.request.UsuarioRequestDTO;
import br.com.smartinvest.smart_invest_api.DTO.response.UsuarioResponseDTO;
import br.com.smartinvest.smart_invest_api.model.Usuario;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        return Usuario.builder()
                .nome(usuarioRequestDTO.nome())
                .tipo(usuarioRequestDTO.tipo())
                .build();
    }

    public static UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .nome(usuario.getNome())
                .tipo(usuario.getTipo())
                .build();
    }
}
