package pe.devstream.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.devstream.repository.model.ProgramaEstudiante;
@Repository
public interface ProgramaEstudianteRepository extends ReactiveCrudRepository<ProgramaEstudiante, Long> {
}
