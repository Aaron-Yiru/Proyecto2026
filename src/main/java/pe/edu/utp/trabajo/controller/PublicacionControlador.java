package pe.edu.utp.trabajo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.trabajo.dto.PublicacionPeticionDTO;
import pe.edu.utp.trabajo.dto.PublicacionRespuestaDTO;
import pe.edu.utp.trabajo.service.PublicacionService;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
@RequiredArgsConstructor
public class PublicacionControlador {

    private final PublicacionService publicacionServicio;

    @PostMapping
    public ResponseEntity<PublicacionRespuestaDTO> crearPublicacion(@RequestBody PublicacionPeticionDTO peticion) {
        PublicacionRespuestaDTO respuesta = publicacionServicio.crearPublicacion(peticion);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PublicacionRespuestaDTO>> listarPublicaciones() {
        List<PublicacionRespuestaDTO> respuesta = publicacionServicio.obtenerTodasLasPublicaciones();
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}