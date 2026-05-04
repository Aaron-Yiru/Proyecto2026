package pe.edu.utp.trabajo.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.utp.trabajo.model.Comentario;
import pe.edu.utp.trabajo.repository.ComentarioRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioService {
    private final ComentarioRepository repository;

    public Comentario guardar(Long confesionId, Comentario comentario) {
        comentario.setConfesionId(confesionId);
        if (comentario.getFechaComentario() == null) {
            comentario.setFechaComentario(LocalDateTime.now());
        }
        return repository.save(comentario);
    }

    public List<Comentario> buscarPorConfesion(Long confesionId) {
        return repository.findByConfesionId(confesionId);
    }
}