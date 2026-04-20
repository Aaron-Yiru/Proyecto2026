package pe.edu.utp.trabajo.service.implementacion;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.utp.trabajo.dto.PublicacionPeticionDTO;
import pe.edu.utp.trabajo.dto.PublicacionRespuestaDTO;
import pe.edu.utp.trabajo.model.Publicacion;
import pe.edu.utp.trabajo.model.Usuario;
import pe.edu.utp.trabajo.repository.PublicacionRepositorio;
import pe.edu.utp.trabajo.repository.UsuarioRepositorio;
import pe.edu.utp.trabajo.service.PublicacionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicacionServicioImpl implements PublicacionService {

    private final PublicacionRepositorio publicacionRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    @Override
    public PublicacionRespuestaDTO crearPublicacion(PublicacionPeticionDTO peticion) {
        Usuario usuario = usuarioRepositorio.findById(peticion.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + peticion.getUsuarioId()));

        Publicacion nuevaPublicacion = Publicacion.builder()
                .usuario(usuario)
                .contenido(peticion.getContenido())
                .fechaCreacion(LocalDateTime.now())
                .esAnonimo(peticion.isEsAnonimo())
                .build();

        Publicacion publicacionGuardada = publicacionRepositorio.save(nuevaPublicacion);
        return mapearADto(publicacionGuardada);
    }

    @Override
    public List<PublicacionRespuestaDTO> obtenerTodasLasPublicaciones() {
        return publicacionRepositorio.findAll().stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    private PublicacionRespuestaDTO mapearADto(Publicacion publicacion) {
        String nombreAutor = publicacion.isEsAnonimo() ? "Anónimo" : publicacion.getUsuario().getNombres();

        return PublicacionRespuestaDTO.builder()
                .id(publicacion.getId())
                .autor(nombreAutor)
                .contenido(publicacion.getContenido())
                .fechaCreacion(publicacion.getFechaCreacion())
                .esAnonimo(publicacion.isEsAnonimo())
                .build();
    }
}