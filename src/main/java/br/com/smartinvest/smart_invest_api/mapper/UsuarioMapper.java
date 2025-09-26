package br.com.smartinvest.smart_invest_api.mapper;

import br.com.smartinvest.smart_invest_api.DTO.request.UsuarioRequestDTO;
import br.com.smartinvest.smart_invest_api.DTO.response.UsuarioResponseDTO;
import br.com.smartinvest.smart_invest_api.model.Usuario;
import br.com.smartinvest.smart_invest_api.util.RandomUtil;

import java.util.Date;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        return Usuario.builder()
                .nome(RandomUtil.getRandomAlphaNumeric(5)) // agora vem da classe util
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
}
