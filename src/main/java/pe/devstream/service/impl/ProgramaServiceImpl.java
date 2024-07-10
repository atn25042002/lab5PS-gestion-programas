package pe.devstream.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.devstream.controller.request.MentorRequest;
import pe.devstream.controller.request.ProgramaRequest;
import pe.devstream.controller.request.SupervisorRequest;
import pe.devstream.repository.*;
import pe.devstream.repository.model.*;
import pe.devstream.service.ProgramaService;
import pe.devstream.service.exception.ProgramNameAlreadyTakenException;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProgramaServiceImpl implements ProgramaService {

  private final ProgramaRepository programaRepository;
  private final SupervisorProgramaRepository supervisorProgramaRepository;
  private final MentorProgramaRepository mentorProgramaRepository;
  private final ProgramaParametrosRepository programaParametrosRepository;
  private final ProgramaClaseRepository programaClaseRepository;
  @Override
  @Transactional
  public Mono<Object> crearPrograma(ProgramaRequest model) {
    return programaRepository.findByNombre(model.getNombre())
      .flatMap(programa -> Mono.error(new ProgramNameAlreadyTakenException("Ya existe un programa con el mismo nombre.")))
      .switchIfEmpty(crearProgramaAndRelatedEntities(model));
  }

  private Mono<String> crearProgramaAndRelatedEntities(ProgramaRequest model) {
    return programaRepository.save(toEntityPrograma(model))
      .flatMap(savedPrograma -> {
        var supervisoresSaved = supervisorProgramaRepository.saveAll(
          convertToSupervisorPrograma(model.getSupervisores(), savedPrograma.getId())
        ).onErrorStop();
        var mentoresSaved = mentorProgramaRepository.saveAll(
          convertToMentorPrograma(model.getMentores(), savedPrograma.getId())
        ).onErrorStop();
        var programaParametrosSaved = programaParametrosRepository.saveAll(
          convertToParametrosPrograma(model.getHerramientas(), savedPrograma.getId())
        ).onErrorStop();
        var programaClaseSaved = programaClaseRepository.saveAll(
          convertToParametrosPrograma(model, savedPrograma.getId())
        ).onErrorStop();

        return Mono.when(supervisoresSaved, mentoresSaved, programaParametrosSaved, programaClaseSaved)
          .then(Mono.just("Programa creado exitosamente"));
      });
  }
  private Programa toEntityPrograma(ProgramaRequest model){
    return Programa.builder()
      .descripcion(model.getDescripcion())
      .nombre(model.getNombre())
      .fechaInicio(model.getFechaInicio())
      .fechaFin(model.getFechaFin())
      .fechaInicioReal(null)
      .fechaFinReal(null)
      .build();
  }
  private List<SupervisorPrograma> convertToSupervisorPrograma(List<SupervisorRequest> supervisorRequests, Long idPrograma) {
    return supervisorRequests.stream()
      .map(supervisorRequest -> convertToSupervisorPrograma(supervisorRequest, idPrograma))
      .toList();
  }
  private SupervisorPrograma convertToSupervisorPrograma(SupervisorRequest supervisorRequest, Long idPrograma) {
    return SupervisorPrograma.builder()
      .idPrograma(idPrograma)
      .correo(supervisorRequest.getCorreo())
      .nombre(supervisorRequest.getNombre())
      .status((short) 1)
      .build();
  }
  private List<MentorPrograma> convertToMentorPrograma(List<MentorRequest>mentorRequests, Long idPrograma) {
    return mentorRequests.stream()
      .map(mentorRequest -> convertToMentorPrograma(mentorRequest, idPrograma))
      .toList();
  }
  private MentorPrograma convertToMentorPrograma(MentorRequest supervisorRequest, Long idPrograma) {
    return MentorPrograma.builder()
      .idPrograma(idPrograma)
      .correo(supervisorRequest.getCorreo())
      .nombre(supervisorRequest.getNombre())
      .status((short) 1)
      .build();
  }
  private List<ProgramaParametros> convertToParametrosPrograma(List<Long> herramientas, Long idPrograma) {
    return herramientas.stream()
      .map(herramienta -> convertToParametrosPrograma(herramienta, idPrograma))
      .toList();
  }
  private ProgramaParametros convertToParametrosPrograma(Long idHerramienta, Long idPrograma) {
    return ProgramaParametros.builder()
      .idPrograma(idPrograma)
      .idParametros(idHerramienta)
      .build();
  }
  private List<ProgramaClase> convertToParametrosPrograma(ProgramaRequest programaRequest, Long idPrograma) {
    return programaRequest.getClases().stream()
      .map(claseId -> convertToClasePrograma(claseId, idPrograma, programaRequest.getCreatedBy()))
      .toList();
  }
  private ProgramaClase convertToClasePrograma(Long idClase, Long idPrograma, String createdBy) {
    return ProgramaClase.builder()
      .createdBy(createdBy)
      .createdAt(java.sql.Date.valueOf(LocalDate.now()))
      .modifyAt(null)
      .modifyBy(null)
      .status((short) 1)
      .idPrograma(idPrograma)
      .idClase(idClase)
      .isFinished(0L)
      .build();
  }

}
