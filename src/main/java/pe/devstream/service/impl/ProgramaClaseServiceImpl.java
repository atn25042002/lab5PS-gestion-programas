package pe.devstream.service.impl;

import java.sql.Timestamp;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import pe.devstream.controller.response.CategoriaDatosResponse;
import pe.devstream.controller.response.ProgramaClaseResponse;
import pe.devstream.controller.response.ProgramaDatosResponse;
import pe.devstream.repository.ProgramaClaseR2DBCRepository;
import pe.devstream.repository.ProgramaClaseRepository;
import pe.devstream.repository.impl.ProgramaClaseR2DBCRepositoryImpl;
import pe.devstream.repository.model.ProgramaClase;
import pe.devstream.service.ProgramaClaseService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProgramaClaseServiceImpl implements ProgramaClaseService{
  private final ProgramaClaseR2DBCRepository programaClaseR2dbcRepository;

  @Override
  public Mono<List<ProgramaClaseResponse>> listarProgramasClase() {
    return programaClaseR2dbcRepository.getAllProgramaClases().collectList();
  }

  @Override
  public Mono<List<CategoriaDatosResponse>> listarClases(Long idPrograma){
    return programaClaseR2dbcRepository.getAllClasesPorPrograma(idPrograma).collectList();
  }

  @Override
  public Mono<ProgramaDatosResponse> datosPrograma(Long idPrograma){
    return programaClaseR2dbcRepository.getNombrePrograma(idPrograma);
  }
}
