package pe.devstream.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.devstream.repository.model.Programa;
import reactor.core.publisher.Mono;

@Repository
public interface ProgramaRepository  extends ReactiveCrudRepository<Programa, Long> {
  Mono<Programa> findByNombre(String nombre);
}