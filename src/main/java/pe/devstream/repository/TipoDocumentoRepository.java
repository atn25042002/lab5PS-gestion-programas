package pe.devstream.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.devstream.repository.model.TipoDocumento;

public interface TipoDocumentoRepository extends ReactiveCrudRepository<TipoDocumento, Long> {
}