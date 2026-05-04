package pe.edu.utp.trabajo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.trabajo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
