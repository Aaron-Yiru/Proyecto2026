package pe.edu.utp.trabajo.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Comentario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;
    private LocalDateTime fechaComentario = LocalDateTime.now();
    private Long confesionId;
    private Long autorId;
}