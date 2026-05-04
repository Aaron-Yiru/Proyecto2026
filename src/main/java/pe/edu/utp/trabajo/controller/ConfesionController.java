package pe.edu.utp.trabajo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.trabajo.model.Confesion;
import pe.edu.utp.trabajo.service.ConfesionService;

import java.util.List;

@RestController
@RequestMapping("/api/confesiones")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ConfesionController {
    private final ConfesionService service;

    // Operación 4: Crear confesión
    @PostMapping
    public ResponseEntity<Confesion> publicarConfesion(@RequestBody Confesion confesion) {
        return new ResponseEntity<>(service.guardar(confesion), HttpStatus.CREATED);
    }

    // Operación 5: Obtener todas las confesiones
    @GetMapping
    public ResponseEntity<List<Confesion>> listarConfesiones() {
        return ResponseEntity.ok(service.listarTodas());
    }

    // Operación 6: Obtener confesión específica
    @GetMapping("/{id}")
    public ResponseEntity<Confesion> obtenerConfesion(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // Operación 7: Actualizar confesión (editar)
    @PutMapping("/{id}")
    public ResponseEntity<Confesion> actualizarConfesion(@PathVariable Long id, @RequestBody Confesion confesion) {
        return ResponseEntity.ok(service.actualizar(id, confesion));
    }

    // Operación 8: Eliminar confesión
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConfesion(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
