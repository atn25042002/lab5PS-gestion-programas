package pe.devstream.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.devstream.repository.model.NivelEstudiante;

public interface NivelEstudianteRepository extends ReactiveCrudRepository<NivelEstudiante, Long> {
}
