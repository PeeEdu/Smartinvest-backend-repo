package br.com.smartinvest.smart_invest_api.service;

import br.com.smartinvest.smart_invest_api.DTO.request.UsuarioRequestDTO;
import br.com.smartinvest.smart_invest_api.DTO.response.UsuarioResponseDTO;
import br.com.smartinvest.smart_invest_api.mapper.UsuarioMapper;
import br.com.smartinvest.smart_invest_api.model.Usuario;
import br.com.smartinvest.smart_invest_api.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public UsuarioResponseDTO saveUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRequestDTO.tipo() == null) {
            throw new RuntimeException("Precisa de um usuário");
        }

        Usuario usuario = UsuarioMapper.toUsuario(usuarioRequestDTO);
        usuarioRepository.save(usuario);
        return UsuarioMapper.toUsuarioResponseDTO(usuario);
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
