package pe.devstream.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.devstream.repository.model.Parametros;

public interface ParametrosRepository extends ReactiveCrudRepository<Parametros, Long> {
}