package pe.edu.utp.trabajo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.trabajo.model.Reaccion;
import java.util.List;

public interface ReaccionRepository extends JpaRepository<Reaccion, Long> {
    List<Reaccion> findByConfesionId(Long confesionId); // Búsqueda personalizada
}