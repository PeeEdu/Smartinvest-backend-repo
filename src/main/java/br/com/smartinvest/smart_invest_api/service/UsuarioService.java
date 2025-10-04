package br.com.smartinvest.smart_invest_api.service;

import br.com.smartinvest.smart_invest_api.DTO.request.UsuarioRequestDTO;
import br.com.smartinvest.smart_invest_api.DTO.response.UsuarioResponseDTO;
import br.com.smartinvest.smart_invest_api.Util.RandomUtil;
import br.com.smartinvest.smart_invest_api.enums.TipoUsuario;
import br.com.smartinvest.smart_invest_api.mapper.UsuarioMapper;
import br.com.smartinvest.smart_invest_api.model.Usuario;
import br.com.smartinvest.smart_invest_api.repository.UsuarioRepository;
import br.com.smartinvest.smart_invest_api.Util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario saveUsuario(TipoUsuario tipoUsuario) {
        if (tipoUsuario == null) {
            throw new RuntimeException("Precisa de um tipo de usuário");
        }

        Usuario usuario = Usuario.builder()
                .nome(RandomUtil.getRandomAlphaNumeric(5))
                .simulacao(null)
                .dataCriacao(new Date())
                .tipo(tipoUsuario)
                .build();
        usuarioRepository.save(usuario);
        return usuario ;
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public UsuarioResponseDTO updateUsuario(UsuarioRequestDTO usuarioRequestDTO, Long id) {
        Usuario usuario = getUsuarioById(id);
        if(usuario == null){
            throw new RuntimeException("Usuario nao encontrado");
        }

        usuario.setTipo(usuarioRequestDTO.tipo());
        usuarioRepository.save(usuario);
        return UsuarioMapper.toUsuarioResponseDTO(usuario);
    }

}