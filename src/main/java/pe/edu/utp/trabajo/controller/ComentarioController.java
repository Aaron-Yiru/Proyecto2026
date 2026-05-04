package pe.edu.utp.trabajo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.utp.trabajo.model.Comentario;
import pe.edu.utp.trabajo.service.ComentarioService;

import java.util.List;

@RestController
@RequestMapping("/api/confesiones/{confesionId}/comentarios")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ComentarioController {
    private final ComentarioService service;

    // Operación 9: Agregar comentario a una confesión
    @PostMapping
    public ResponseEntity<Comentario> agregarComentario(@PathVariable Long confesionId, @RequestBody Comentario comentario) {
        return new ResponseEntity<>(service.guardar(confesionId, comentario), HttpStatus.CREATED);
    }

    // Operación 10: Listar comentarios de una confesión
    @GetMapping
    public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable Long confesionId) {
        return ResponseEntity.ok(service.buscarPorConfesion(confesionId));
    }
}
