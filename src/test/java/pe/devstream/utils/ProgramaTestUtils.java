package pe.devstream.utils;

import pe.devstream.controller.request.MentorRequest;
import pe.devstream.controller.request.ProgramaRequest;
import pe.devstream.controller.request.SupervisorRequest;
import pe.devstream.repository.model.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;

public class ProgramaTestUtils {
  private static ProgramaRequest programaRequest;
  private static Programa programa;
  private static ProgramaClase programaClase;
  private static ProgramaParametros programaParametros;
  private static MentorPrograma mentorPrograma;
  private static SupervisorPrograma supervisorPrograma;
  public static ProgramaRequest getProgramaRequest() {
    if (programaRequest == null) {
      programaRequest = ProgramaRequest.builder()
        .descripcion("Description")
        .nombre("Program Name")
        .createdBy("Creator")
        .fechaInicio(new Timestamp(System.currentTimeMillis()))
        .fechaFin(new Timestamp(System.currentTimeMillis()))
        .supervisores(Arrays.asList(
          SupervisorRequest.builder()
            .correo("supervisor1@example.com")
            .nombre("Supervisor 1")
            .status(1)
            .build(),
          SupervisorRequest.builder()
            .correo("supervisor2@example.com")
            .nombre("Supervisor 2")
            .status(1)
            .build()
        ))
        .mentores(Arrays.asList(
          MentorRequest.builder()
            .correo("mentor1@example.com")
            .nombre("Mentor 1")
            .status(1)
            .build(),
          MentorRequest.builder()
            .correo("mentor2@example.com")
            .nombre("Mentor 2")
            .status(1)
            .build()
        ))
        .herramientas(Arrays.asList(1L, 2L, 3L))
        .clases(Arrays.asList(4L, 5L, 6L))
        .build();
    }
    return programaRequest;
  }
  public static Programa getPrograma() {
    if (programa == null) {
      programa = Programa.builder()
        .id(1L)
        .descripcion("Description")
        .nombre("Program Name")
        .fechaInicio(new Timestamp(System.currentTimeMillis()))
        .fechaFin(new Timestamp(System.currentTimeMillis()))
        .fechaInicioReal(new Timestamp(System.currentTimeMillis()))
        .fechaFinReal(new Timestamp(System.currentTimeMillis()))
        .build();
    }
    return programa;
  }

  public static ProgramaClase getProgramaClase() {
    if (programaClase == null) {
      programaClase = ProgramaClase.builder()
        .id(1L)
        .createdBy("John Doe")
        .createdAt(java.sql.Date.valueOf(LocalDate.now()))
        .modifyBy("Jane Smith")
        .modifyAt(null)
        .status((short) 1)
        .idPrograma(100L)
        .isFinished(0L)
        .idClase(200L)
        .build();
    }
    return programaClase;
  }
  public static ProgramaParametros getProgramaParametros() {
    if (programaParametros == null) {
      programaParametros = ProgramaParametros.builder()
        .id(1L)
        .idPrograma(100L)
        .idParametros(200L)
        .build();
    }
    return programaParametros;
  }

  public static MentorPrograma getMentorPrograma() {
    if (mentorPrograma == null) {
      mentorPrograma = MentorPrograma.builder()
        .id(1L)
        .idPrograma(100L)
        .correo("mentor@example.com")
        .nombre("Mentor Name")
        .status((short) 1)
        .build();
    }
    return mentorPrograma;
  }
  public static SupervisorPrograma getSupervisorPrograma() {
    if (supervisorPrograma == null) {
      supervisorPrograma = SupervisorPrograma.builder()
        .id(1L)
        .idPrograma(100L)
        .correo("supervisor@example.com")
        .nombre("Supervisor Name")
        .status((short) 1)
        .build();
    }
    return supervisorPrograma;
  }
}
