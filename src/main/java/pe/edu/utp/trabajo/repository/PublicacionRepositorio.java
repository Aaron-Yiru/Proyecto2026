package pe.edu.utp.trabajo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.trabajo.model.Publicacion;

@Repository
public interface PublicacionRepositorio extends JpaRepository<Publicacion, Long> {
}
