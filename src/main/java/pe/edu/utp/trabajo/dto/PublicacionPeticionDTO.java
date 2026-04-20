package pe.edu.utp.trabajo.dto;

import lombok.Data;

@Data
public class PublicacionPeticionDTO {
    private Long usuarioId;
    private String contenido;
    private boolean esAnonimo;
}