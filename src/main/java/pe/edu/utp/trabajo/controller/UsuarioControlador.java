package pe.edu.utp.trabajo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.trabajo.dto.UsuarioPeticionDTO;
import pe.edu.utp.trabajo.dto.UsuarioRespuestaDTO;
import pe.edu.utp.trabajo.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioControlador {

    private final UsuarioService usuarioServicio;

    @PostMapping
    public ResponseEntity<UsuarioRespuestaDTO> crearUsuario(@RequestBody UsuarioPeticionDTO peticion) {
        UsuarioRespuestaDTO respuesta = usuarioServicio.crearUsuario(peticion);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioRespuestaDTO>> listarUsuarios() {
        List<UsuarioRespuestaDTO> respuesta = usuarioServicio.obtenerTodosLosUsuarios();
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
