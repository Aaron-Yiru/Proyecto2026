package pe.edu.utp.trabajo.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.utp.trabajo.model.Reaccion;
import pe.edu.utp.trabajo.repository.ReaccionRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaccionService {
    private final ReaccionRepository repository;

    public Reaccion guardar(Long confesionId, Reaccion reaccion) {
        reaccion.setConfesionId(confesionId);
        if (reaccion.getFechaReaccion() == null) {
            reaccion.setFechaReaccion(LocalDateTime.now());
        }
        return repository.save(reaccion);
    }

    public List<Reaccion> buscarPorConfesion(Long confesionId) {
        return repository.findByConfesionId(confesionId);
    }
}