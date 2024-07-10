package pe.devstream.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.devstream.repository.model.SupervisorPrograma;
@Repository
public interface SupervisorProgramaRepository extends ReactiveCrudRepository<SupervisorPrograma, Long> {
}