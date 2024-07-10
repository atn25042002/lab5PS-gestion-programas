package pe.devstream.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.devstream.repository.model.MentorPrograma;
@Repository
public interface MentorProgramaRepository extends ReactiveCrudRepository<MentorPrograma, Long> {
}