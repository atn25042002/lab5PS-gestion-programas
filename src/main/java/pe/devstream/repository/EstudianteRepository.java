package pe.devstream.repository;

import org.springframework.stereotype.Repository;
import pe.devstream.controller.response.EstudianteResponse;
import reactor.core.publisher.Flux;
@Repository
public interface EstudianteRepository  {
  Flux<EstudianteResponse> getAllEstudiantesPorPrograma(Long id);

}

