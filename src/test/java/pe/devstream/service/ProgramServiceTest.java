package pe.devstream.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.devstream.controller.request.ProgramaRequest;
import pe.devstream.repository.*;
import pe.devstream.repository.model.*;
import pe.devstream.service.exception.ProgramNameAlreadyTakenException;
import pe.devstream.service.impl.ProgramaServiceImpl;
import pe.devstream.utils.ProgramaTestUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProgramServiceTest {
  @Mock
  private ProgramaRepository programaRepository;
  @Mock
  private SupervisorProgramaRepository supervisorProgramaRepository;
  @Mock
  private MentorProgramaRepository mentorProgramaRepository;
  @Mock
  private ProgramaParametrosRepository programaParametrosRepository;
  @Mock
  private ProgramaClaseRepository programaClaseRepository;
  @InjectMocks
  private ProgramaServiceImpl programaService;

  @Test
  void testCrearPrograma() {
    // Mock data
    ProgramaRequest mockRequest = ProgramaTestUtils.getProgramaRequest();
    Programa mockPrograma = ProgramaTestUtils.getPrograma();
    List<ProgramaClase> programaClases = Arrays.asList(ProgramaTestUtils.getProgramaClase(), ProgramaTestUtils.getProgramaClase());
    List<ProgramaParametros> programaParametrosList = Arrays.asList(ProgramaTestUtils.getProgramaParametros(), ProgramaTestUtils.getProgramaParametros());
    List<MentorPrograma> mentorProgramas = Arrays.asList(ProgramaTestUtils.getMentorPrograma(), ProgramaTestUtils.getMentorPrograma());
    List<SupervisorPrograma> supervisorProgramas = Arrays.asList(ProgramaTestUtils.getSupervisorPrograma(), ProgramaTestUtils.getSupervisorPrograma());

    // Mock the behavior of repositories
    when(programaRepository.findByNombre(anyString())).thenReturn(Mono.empty());
    when(programaRepository.save(any(Programa.class))).thenReturn(Mono.just(mockPrograma));
    when(supervisorProgramaRepository.saveAll(anyList())).thenReturn(Flux.fromIterable(supervisorProgramas));
    when(mentorProgramaRepository.saveAll(anyList())).thenReturn(Flux.fromIterable(mentorProgramas));
    when(programaParametrosRepository.saveAll(anyList())).thenReturn(Flux.fromIterable(programaParametrosList));
    when(programaClaseRepository.saveAll(anyList())).thenReturn(Flux.fromIterable(programaClases));

    StepVerifier.create(programaService.crearPrograma(mockRequest))
      .expectNext("Programa creado exitosamente")
      .verifyComplete();

  }

  @Test
  void givenExistingProgramName_shouldThrowProgramNameAlreadyTakenException() {

    ProgramaRequest mockRequest = ProgramaTestUtils.getProgramaRequest();
    Programa mockPrograma = ProgramaTestUtils.getPrograma();

    // Mock the behavior of repositories
    when(programaRepository.findByNombre(anyString()))
      .thenReturn(Mono.just(mockPrograma));
    when(programaRepository.save(any(Programa.class))).thenReturn(Mono.just(mockPrograma));
    // Assert
    StepVerifier.create(programaService.crearPrograma(mockRequest))
      .expectError(ProgramNameAlreadyTakenException.class)
      .verify();
  }

}