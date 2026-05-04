package pe.edu.utp.trabajo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.trabajo.model.Comentario;
import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByConfesionId(Long confesionId); // Búsqueda personalizada
}
