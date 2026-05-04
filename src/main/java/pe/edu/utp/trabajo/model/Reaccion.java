package pe.edu.utp.trabajo.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reacciones")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Reaccion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoReaccion; // Ej: "LIKE", "ME_DIVIERTE"
    private LocalDateTime fechaReaccion = LocalDateTime.now();
    private Long confesionId;
    private Long usuarioId;
}
