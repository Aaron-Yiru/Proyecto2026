package pe.edu.utp.trabajo.dto;

import lombok.Data;

@Data
public class UsuarioPeticionDTO {
    private String nombres;
    private String correo;
    private String contrasena;
    private Long institucionId;
    private String rol;
}