package pe.devstream.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.devstream.repository.model.ProgramaClase;
import reactor.core.publisher.Flux;

public interface ProgramaClaseRepository extends ReactiveCrudRepository<ProgramaClase, Long> {
    Flux<ProgramaClase> findByIdPrograma(Long idPrograma);
}