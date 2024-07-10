package pe.devstream.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.devstream.repository.model.Nivel;

public interface NivelRepository  extends ReactiveCrudRepository<Nivel, Long> {
}
