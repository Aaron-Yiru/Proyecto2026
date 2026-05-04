package pe.edu.utp.trabajo.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.utp.trabajo.model.Confesion;
import pe.edu.utp.trabajo.repository.ConfesionRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfesionService {
    private final ConfesionRepository repository;

    public Confesion guardar(Confesion confesion) {
        if (confesion.getFechaPublicacion() == null) {
            confesion.setFechaPublicacion(LocalDateTime.now());
        }
        return repository.save(confesion);
    }

    public List<Confesion> listarTodas() {
        return repository.findAll();
    }

    public Confesion buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Confesión no encontrada con el ID: " + id));
    }

    public Confesion actualizar(Long id, Confesion confesionActualizada) {
        Confesion existente = buscarPorId(id);
        existente.setContenido(confesionActualizada.getContenido());
        existente.setEsAnonimo(confesionActualizada.isEsAnonimo());
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        Confesion existente = buscarPorId(id);
        repository.delete(existente);
    }
}