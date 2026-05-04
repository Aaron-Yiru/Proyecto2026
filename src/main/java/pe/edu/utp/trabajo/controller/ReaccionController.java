package pe.edu.utp.trabajo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.trabajo.model.Reaccion;
import pe.edu.utp.trabajo.service.ReaccionService;

import java.util.List;

@RestController
@RequestMapping("/api/confesiones/{confesionId}/reacciones")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ReaccionController {

    private final ReaccionService service;

    // Agregar reacción a una confesión (ej: LIKE)
    @PostMapping
    public ResponseEntity<Reaccion> agregarReaccion(@PathVariable Long confesionId, @RequestBody Reaccion reaccion) {
        return new ResponseEntity<>(service.guardar(confesionId, reaccion), HttpStatus.CREATED);
    }

    // Listar reacciones de una confesión
    @GetMapping
    public ResponseEntity<List<Reaccion>> listarReacciones(@PathVariable Long confesionId) {
        return ResponseEntity.ok(service.buscarPorConfesion(confesionId));
    }
}
