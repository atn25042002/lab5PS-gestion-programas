package pe.devstream.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.devstream.repository.model.Estudiante;
import reactor.core.publisher.Flux;

@Repository
public interface EstudianteR2DBCRepository extends ReactiveCrudRepository<Estudiante, Long> {
  Flux<Estudiante> findByIdTipoDocumentoAndNroDocumento(Long idTipoDocumento, String nroDocumento);
}
