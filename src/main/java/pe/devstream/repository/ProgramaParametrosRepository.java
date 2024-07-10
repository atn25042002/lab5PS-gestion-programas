package pe.devstream.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.devstream.repository.model.ProgramaParametros;

public interface ProgramaParametrosRepository extends ReactiveCrudRepository<ProgramaParametros, Long> {
}