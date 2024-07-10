package pe.devstream.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.devstream.controller.request.EstudianteRequest;
import pe.devstream.controller.request.ProgramaEstudiantesRequest;
import pe.devstream.controller.response.EstudianteResponse;
import pe.devstream.repository.EstudianteR2DBCRepository;
import pe.devstream.repository.EstudianteRepository;
import pe.devstream.repository.NivelEstudianteRepository;
import pe.devstream.repository.ProgramaEstudianteRepository;
import pe.devstream.repository.model.Estudiante;
import pe.devstream.service.exception.EstudiantesDuplicatedException;
import pe.devstream.service.impl.EstudianteServiceImpl;
import pe.devstream.utils.EstudianteTestUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceTest {
  @Mock
  private EstudianteRepository estudianteRepository;

  @Mock
  private EstudianteR2DBCRepository estudianteR2DBCRepository;

  @Mock
  private NivelEstudianteRepository nivelEstudianteRepository;

  @Mock
  private ProgramaEstudianteRepository programaEstudianteRepository;

  @InjectMocks
  private EstudianteServiceImpl estudianteService;

  @Test
  void testListarEstudiantePorPrograma() {
    Long programId = 1L;
    List<EstudianteResponse> expectedEstudiantes = Arrays.asList(
      EstudianteTestUtils.getEstudianteResponse(), EstudianteTestUtils.getEstudianteResponse()
    );

    when(estudianteRepository.getAllEstudiantesPorPrograma(programId))
      .thenReturn(Flux.fromIterable(expectedEstudiantes));

    StepVerifier.create(estudianteService.listarEstudiantePorPrograma(programId))
      .expectNext(expectedEstudiantes)
      .verifyComplete();
  }
  @Test
  void test_successfully_saves_new_students() {

    ProgramaEstudiantesRequest programaEstudiantes =  EstudianteTestUtils.getProgramaEstudiantesRequest();

    when(estudianteR2DBCRepository.findByIdTipoDocumentoAndNroDocumento(anyLong(), anyString()))
      .thenReturn(Flux.empty());
    when(estudianteR2DBCRepository.save(any()))
      .thenReturn(Mono.just(EstudianteTestUtils.getEstudiante()));
    when(nivelEstudianteRepository.saveAll(anyList())).thenReturn(Flux.empty());

    when(programaEstudianteRepository.saveAll(anyList())).thenReturn(Flux.empty());

    StepVerifier.create(estudianteService.guardarEstudiantes(programaEstudiantes))
      .expectNext("Estudiantes creados exitosamente")
      .verifyComplete();
  }
  @Test
  void test_fails_to_save_new_students_with_error_saving_to_nivel_estudiante_repository() {
    ProgramaEstudiantesRequest programaEstudiantes =  EstudianteTestUtils.getProgramaEstudiantesRequest();
    List<Estudiante>  estudiantes = Arrays.asList(
      EstudianteTestUtils.getEstudiante(), EstudianteTestUtils.getEstudiante()
    );

    when(estudianteR2DBCRepository.findByIdTipoDocumentoAndNroDocumento(anyLong(), anyString()))
      .thenReturn(Flux.fromIterable(estudiantes));

    StepVerifier.create(estudianteService.guardarEstudiantes(programaEstudiantes))
      .expectError(EstudiantesDuplicatedException.class)
      .verify();
  }
}
