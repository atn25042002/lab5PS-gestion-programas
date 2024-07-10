package pe.devstream.repository;

import org.springframework.stereotype.Repository;

import pe.devstream.controller.response.CategoriaDatosResponse;
import pe.devstream.controller.response.ProgramaClaseResponse;
import pe.devstream.controller.response.ProgramaDatosResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProgramaClaseR2DBCRepository {
    Flux<CategoriaDatosResponse> getAllClasesPorPrograma(Long id_programa);
    public Flux<ProgramaClaseResponse> getAllProgramaClases();
    public Mono<ProgramaDatosResponse> getNombrePrograma(Long id_programa);
}
