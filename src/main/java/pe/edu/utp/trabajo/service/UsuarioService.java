package pe.edu.utp.trabajo.service;


import pe.edu.utp.trabajo.dto.UsuarioPeticionDTO;
import pe.edu.utp.trabajo.dto.UsuarioRespuestaDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioRespuestaDTO crearUsuario(UsuarioPeticionDTO peticion);
    List<UsuarioRespuestaDTO> obtenerTodosLosUsuarios();
}
