package pe.edu.utp.trabajo.service;

import pe.edu.utp.trabajo.dto.PublicacionPeticionDTO;
import pe.edu.utp.trabajo.dto.PublicacionRespuestaDTO;

import java.util.List;

public interface PublicacionService {
    PublicacionRespuestaDTO crearPublicacion(PublicacionPeticionDTO peticion);
    List<PublicacionRespuestaDTO> obtenerTodasLasPublicaciones();
}
