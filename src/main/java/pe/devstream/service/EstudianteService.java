package pe.devstream.service;

import pe.devstream.controller.request.ProgramaEstudiantesRequest;
import pe.devstream.controller.response.EstudianteResponse;
import reactor.core.publisher.Mono;
import java.util.List;
public interface EstudianteService {
  Mono<List<EstudianteResponse>> listarEstudiantePorPrograma(Long id);
  Mono<Object> guardarEstudiantes(ProgramaEstudiantesRequest programaEstudiantes);
}
