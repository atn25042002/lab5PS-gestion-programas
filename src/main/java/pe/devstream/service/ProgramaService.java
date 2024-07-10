package pe.devstream.service;

import pe.devstream.controller.request.ProgramaRequest;
import reactor.core.publisher.Mono;

public interface ProgramaService {
  Mono<Object> crearPrograma(ProgramaRequest model);
}
