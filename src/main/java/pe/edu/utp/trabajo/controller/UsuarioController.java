package pe.edu.utp.trabajo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.trabajo.model.Usuario;
import pe.edu.utp.trabajo.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    // Operación 1: Crear usuario
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(service.guardar(usuario), HttpStatus.CREATED);
    }

    // Operación 2: Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // Operación 3: Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(service.listarTodos());
    }



}
