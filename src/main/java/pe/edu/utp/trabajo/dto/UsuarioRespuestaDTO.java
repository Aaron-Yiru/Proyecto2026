package pe.edu.utp.trabajo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioRespuestaDTO {
    private Long id;
    private String nombres;
    private String correo;
    private Long institucionId;
    private String rol;
}
