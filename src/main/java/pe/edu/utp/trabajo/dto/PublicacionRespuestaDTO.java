package pe.edu.utp.trabajo.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class PublicacionRespuestaDTO {
    private Long id;
    private String autor; // Mostrará el nombre o "Anónimo" dependiendo de la lógica
    private String contenido;
    private LocalDateTime fechaCreacion;
    private boolean esAnonimo;
}
