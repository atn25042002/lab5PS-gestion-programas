package pe.devstream.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.devstream.controller.request.EstudianteRequest;
import pe.devstream.controller.request.ProgramaEstudiantesRequest;
import pe.devstream.controller.response.EstudianteResponse;
import pe.devstream.repository.EstudianteR2DBCRepository;
import pe.devstream.repository.EstudianteRepository;
import pe.devstream.repository.NivelEstudianteRepository;
import pe.devstream.repository.ProgramaEstudianteRepository;
import pe.devstream.repository.model.Estudiante;
import pe.devstream.repository.model.NivelEstudiante;
import pe.devstream.repository.model.ProgramaEstudiante;
import pe.devstream.service.EstudianteService;
import pe.devstream.service.exception.EstudiantesDuplicatedException;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService{

  private final EstudianteRepository estudianteRepository;
  private final EstudianteR2DBCRepository estudianteR2DBCRepository;
  private final NivelEstudianteRepository nivelEstudianteRepository;
  private final ProgramaEstudianteRepository programaEstudianteRepository;

  @Override
  public Mono<List<EstudianteResponse>> listarEstudiantePorPrograma(Long id) {
    return estudianteRepository.getAllEstudiantesPorPrograma(id).collectList();
  }

  @Transactional
  public Mono<Object> guardarEstudiantes(ProgramaEstudiantesRequest programaEstudiantes) {
    return Flux.fromIterable(programaEstudiantes.getEstudiantes())
      .flatMap(estudiante -> estudianteR2DBCRepository.findByIdTipoDocumentoAndNroDocumento(estudiante.getIdTipoDocumento(), estudiante.getNroDocumento()))
      .collectList()
      .flatMap(existingEstudiantes -> {
        if (!existingEstudiantes.isEmpty()) {
          return Mono.error(new EstudiantesDuplicatedException("Los siguientes números de documento ya se encuentran registrados en sistema " + concatenateDocumentInfo(existingEstudiantes)));
        }
        return Flux.fromIterable(programaEstudiantes.getEstudiantes())
          .flatMap(this::saveEstudiante)
          .collectList()
          .flatMap(estudiantesSaved -> {
            var nivelEstudianteSaved =  nivelEstudianteRepository.saveAll(
              convertListToNivelEstudiante(estudiantesSaved)
            ).onErrorStop();
            var programaEstudianteSaved =  programaEstudianteRepository.saveAll(
              convertListToProgramaEstudiante(estudiantesSaved, programaEstudiantes.getIdPrograma())
            ).onErrorStop();
            return Mono.when(nivelEstudianteSaved, programaEstudianteSaved)
              .then(Mono.just("Estudiantes creados exitosamente"));
          });
      });
  }

  private Mono<EstudianteRequest> saveEstudiante(EstudianteRequest estudianteRequest) {
    return estudianteR2DBCRepository.save(convertToEstudiante(estudianteRequest))
      .doOnNext(result -> estudianteRequest.setId(result.getId()))
      .thenReturn(estudianteRequest);
  }

  private String concatenateDocumentInfo(List<Estudiante> estudiantes) {
    return estudiantes.stream()
      .map(estudiante -> estudiante.getIdTipoDocumento() + " - " + estudiante.getNroDocumento())
      .collect(Collectors.joining(" ;"));
  }

  private Estudiante convertToEstudiante(EstudianteRequest estudianteRequest) {
    return Estudiante.builder()
      .idTipoDocumento(estudianteRequest.getIdTipoDocumento())
      .nroDocumento(estudianteRequest.getNroDocumento())
      .nombresApellidos(estudianteRequest.getNombresApellidos())
      .idGenero(estudianteRequest.getIdGenero())
      .fechaNacimiento(estudianteRequest.getFechaNacimiento())
      .correo(estudianteRequest.getCorreo())
      .nroCelular(estudianteRequest.getNroCelular())
      .puntajePseudocodigo(estudianteRequest.getPuntajePseudocodigo())
      .puntajeRazonamientoMath(estudianteRequest.getPuntajeRazonamientoMath())
      .cooperativo(estudianteRequest.getCooperativo())
      .facilidadPalabra(estudianteRequest.getFacilidadPalabra())
      .lider(estudianteRequest.getLider())
      .ingenioso(estudianteRequest.getIngenioso())
      .organizado(estudianteRequest.getOrganizado())
      .confrontacional(estudianteRequest.getConfrontacional())
      .aislado(estudianteRequest.getAislado())
      .noEmpatico(estudianteRequest.getNoEmpatico())
      .pais(estudianteRequest.getPais())
      .provincia(estudianteRequest.getProvincia())
      .carrera(estudianteRequest.getCarrera())
      .universidad(estudianteRequest.getUniversidad())
      .ciclo(estudianteRequest.getCiclo())
      .gradoAcademico(estudianteRequest.getGradoAcademico())
      .nivelIngles(estudianteRequest.getNivelIngles())
      .experienciaLaboral(estudianteRequest.getExperienciaLaboral())
      .descripcionExperiencia(estudianteRequest.getDescripcionExperiencia())
      .nivelProgramacion(estudianteRequest.getNivelProgramacion())
      .status(estudianteRequest.getStatus())
      .build();
  }
  public List<NivelEstudiante> convertListToNivelEstudiante(List<EstudianteRequest> estudiantesRequests) {
    return estudiantesRequests.stream()
      .flatMap(estudianteRequest -> estudianteRequest.getNivelLenguajes().stream()
        .map(nivelEstudianteRequest -> NivelEstudiante.builder()
          .idNivel(nivelEstudianteRequest.getIdNivel())
          .idParametros(nivelEstudianteRequest.getIdLenguaje())
          .idEstudiante(estudianteRequest.getId())
          .build()
        )
      )
      .collect(Collectors.toList());
  }
  public List<ProgramaEstudiante> convertListToProgramaEstudiante(List<EstudianteRequest> estudiantesRequests, Long idPrograma) {
    return estudiantesRequests.stream()
      .map(estudianteRequest -> ProgramaEstudiante.builder()
          .idEstudiante(estudianteRequest.getId())
          .idPrograma(idPrograma)
          .build()
      )
      .collect(Collectors.toList());
  }

}
