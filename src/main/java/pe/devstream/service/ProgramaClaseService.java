package pe.devstream.service;

import java.util.List;

import pe.devstream.controller.response.CategoriaDatosResponse;
import pe.devstream.controller.response.ProgramaClaseResponse;
import pe.devstream.controller.response.ProgramaDatosResponse;
import reactor.core.publisher.Mono;

public interface ProgramaClaseService {
    Mono<List<ProgramaClaseResponse>> listarProgramasClase();
    Mono<List<CategoriaDatosResponse>> listarClases(Long idPrograma);
    Mono<ProgramaDatosResponse> datosPrograma(Long idPrograma);
}
