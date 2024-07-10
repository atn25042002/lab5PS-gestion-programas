package pe.devstream.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.devstream.repository.model.Genero;
@Repository
public interface GeneroRepository extends ReactiveCrudRepository<Genero, Long> {
}
