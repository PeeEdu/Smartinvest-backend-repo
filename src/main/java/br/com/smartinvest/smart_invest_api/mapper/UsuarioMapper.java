package br.com.smartinvest.smart_invest_api.mapper;

import br.com.smartinvest.smart_invest_api.DTO.request.UsuarioRequestDTO;
import br.com.smartinvest.smart_invest_api.DTO.response.UsuarioResponseDTO;
import br.com.smartinvest.smart_invest_api.model.Usuario;

import java.util.Date;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        return Usuario.builder()
                .nome(getRandomAlphaNumeric())
                .tipo(usuarioRequestDTO.tipo())
                .dataCriacao(new Date())
                .build();
    }

    public static UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .dataCriacao(usuario.getDataCriacao())
                .nome(usuario.getNome())
                .tipo(usuario.getTipo())
                .build();
    }

    public static String getRandomAlphaNumeric() {
        int length = 20;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
