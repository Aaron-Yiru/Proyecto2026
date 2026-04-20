package pe.edu.utp.trabajo.service.implementacion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.utp.trabajo.dto.UsuarioPeticionDTO;
import pe.edu.utp.trabajo.dto.UsuarioRespuestaDTO;
import pe.edu.utp.trabajo.model.Usuario;
import pe.edu.utp.trabajo.repository.UsuarioRepositorio;
import pe.edu.utp.trabajo.service.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements UsuarioService {

    private final UsuarioRepositorio usuarioRepositorio;

    @Override
    public UsuarioRespuestaDTO crearUsuario(UsuarioPeticionDTO peticion) {
        Usuario nuevoUsuario = Usuario.builder()
                .nombres(peticion.getNombres())
                .correo(peticion.getCorreo())
                .contrasena(peticion.getContrasena()) // Idealmente aquí deberías encriptar (ej. BCrypt)
                .institucionId(peticion.getInstitucionId())
                .rol(peticion.getRol())
                .build();

        Usuario usuarioGuardado = usuarioRepositorio.save(nuevoUsuario);
        return mapearADto(usuarioGuardado);
    }

    @Override
    public List<UsuarioRespuestaDTO> obtenerTodosLosUsuarios() {
        return usuarioRepositorio.findAll().stream()
                .map(this::mapearADto)
                .collect(Collectors.toList());
    }

    private UsuarioRespuestaDTO mapearADto(Usuario usuario) {
        return UsuarioRespuestaDTO.builder()
                .id(usuario.getId())
                .nombres(usuario.getNombres())
                .correo(usuario.getCorreo())
                .institucionId(usuario.getInstitucionId())
                .rol(usuario.getRol())
                .build();
    }
}