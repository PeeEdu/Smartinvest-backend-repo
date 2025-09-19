package br.com.smartinvest.smart_invest_api.controller;

import br.com.smartinvest.smart_invest_api.DTO.request.UsuarioRequestDTO;
import br.com.smartinvest.smart_invest_api.DTO.response.BaseResponse;
import br.com.smartinvest.smart_invest_api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @Operation(summary = "Listar Usuarios")
    public ResponseEntity<BaseResponse> getUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.builder()
                .message("Usuarios Listados")
                .status("OK")
                .data(usuarioService.getAllUsuarios())
                .build()
        );
    }

    @PostMapping
    @Operation(summary = "Salvar Usuario")
    public ResponseEntity<BaseResponse> saveUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).
                body(BaseResponse.builder()
                        .message("Usuario Salvo")
                        .status("OK")
                        .data(usuarioService.saveUsuario(usuarioRequestDTO))
                        .build()
                );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Usuario")
    public ResponseEntity<BaseResponse> updateUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).
                body(BaseResponse.builder()
                        .message("Usuario Atualizado")
                        .status("OK")
                        .data(usuarioService.updateUsuario(usuarioRequestDTO, id))
                        .build()
                );
    }

}
