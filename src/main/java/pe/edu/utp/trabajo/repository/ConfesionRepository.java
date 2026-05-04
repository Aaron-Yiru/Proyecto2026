package pe.edu.utp.trabajo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.trabajo.model.Confesion;

public interface ConfesionRepository extends JpaRepository<Confesion, Long> {
}