package pe.edu.utp.trabajo.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "confesiones")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Confesion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenido;
    private boolean esAnonimo;
    private LocalDateTime fechaPublicacion = LocalDateTime.now();
    private Long autorId;
}
