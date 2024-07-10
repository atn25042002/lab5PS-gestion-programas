package pe.devstream.utils;

import pe.devstream.controller.request.EstudianteRequest;
import pe.devstream.controller.request.NivelEstudianteRequest;
import pe.devstream.controller.request.ProgramaEstudiantesRequest;
import pe.devstream.controller.response.*;
import pe.devstream.repository.model.Estudiante;

import java.sql.Timestamp;
import java.util.*;

public class EstudianteTestUtils {
  private static Estudiante estudiante;
  private static EstudianteResponse estudianteResponse;
  private static EstudianteRequest estudianteRequest;
  private static ProgramaEstudiantesRequest programaEstudiantesRequest;
  private static List<Map<String, Object>>  estudiantesSqlHashMap;
  public static Estudiante getEstudiante()  {
    if (estudiante == null) {
      estudiante = Estudiante.builder()
        .id(1L)
        .usuario("juan_perez")
        .contrasena("contraseña123")
        .idTipoDocumento(1L)
        .nroDocumento("12345678")
        .nombresApellidos("Juan Pérez")
        .idGenero(1L)
        .fechaNacimiento("1990-01-01")
        .correo("juan@example.com")
        .nroCelular("987654321")
        .puntajePseudocodigo(85.5)
        .puntajeRazonamientoMath(90.0)
        .cooperativo("Sí")
        .facilidadPalabra("Alta")
        .lider("Sí")
        .ingenioso("Sí")
        .organizado("Sí")
        .confrontacional("No")
        .aislado("No")
        .noEmpatico("No")
        .pais("Perú")
        .provincia("Lima")
        .carrera("Ingeniería de Sistemas")
        .universidad("Universidad Nacional Mayor de San Marcos")
        .ciclo("Ciclo VII")
        .gradoAcademico("Bachiller")
        .nivelIngles("Intermedio")
        .experienciaLaboral("Sí")
        .descripcionExperiencia("Desarrollador de software en empresa X")
        .nivelProgramacion("Avanzado")
        .status((short) 1)
        .createdBy("admin")
        .createdAt(new Timestamp(System.currentTimeMillis()))
        .modifyBy("admin")
        .modifyAt(new Timestamp(System.currentTimeMillis()))
        .build();
    }
    return estudiante;
  }
  public static EstudianteResponse getEstudianteResponse()  {
    if (estudianteResponse == null) {
      estudianteResponse = EstudianteResponse.builder()
        .id(1L)
        .tipoDocumento("DNI")
        .nroDocumento("12345678")
        .nombresApellidos("Juan Pérez")
        .genero("Masculino")
        .fechaNacimiento("1990-01-01")
        .correo("juan@example.com")
        .nroCelular("987654321")
        .puntajePseudocodigo(85.5)
        .puntajeRazonamientoMath(90.0)
        .cooperativo("Sí")
        .facilidadPalabra("Alta")
        .lider("Sí")
        .ingenioso("Sí")
        .organizado("Sí")
        .confrontacional("No")
        .aislado("No")
        .noEmpatico("No")
        .pais("Perú")
        .provincia("Lima")
        .carrera("Ingeniería de Sistemas")
        .universidad("Universidad Nacional Mayor de San Marcos")
        .ciclo("Ciclo VII")
        .gradoAcademico("Bachiller")
        .nivelIngles("Intermedio")
        .experienciaLaboral("Sí")
        .descripcionExperiencia("Desarrollador de software en empresa X")
        .nivelProgramacion("Avanzado")
        .status((short) 1)
        .nivelParametros(Arrays.asList(
          NivelEstudianteResponse.builder()
            .id(1L)
            .nivel(NivelReponse.builder().id(1L).descripcion("Avanzado").build())
            .parametros(ParametrosReponse.builder().id(1L).nombre("Algoritmos").build())
            .build(),
          NivelEstudianteResponse.builder()
            .id(2L)
            .nivel(NivelReponse.builder().id(2L).descripcion("Intermedio").build())
            .parametros(ParametrosReponse.builder().id(2L).nombre("Estructuras de datos").build())
            .build()
        ))
        .build();
    }
    return estudianteResponse;
  }
  public static EstudianteRequest getEstudianteRequest(){
    if(estudianteRequest == null ){
      estudianteRequest = EstudianteRequest.builder()
        .id(1L)
        .idTipoDocumento(1L)
        .nroDocumento("12345678")
        .nombresApellidos("Juan Pérez")
        .idGenero(1L)
        .fechaNacimiento("1990-01-01")
        .correo("juan@example.com")
        .nroCelular("987654321")
        .puntajePseudocodigo(85.5)
        .puntajeRazonamientoMath(90.0)
        .cooperativo("Sí")
        .facilidadPalabra("Alta")
        .lider("Sí")
        .ingenioso("Sí")
        .organizado("Sí")
        .confrontacional("No")
        .aislado("No")
        .noEmpatico("No")
        .pais("Perú")
        .provincia("Lima")
        .carrera("Ingeniería de Sistemas")
        .universidad("Universidad Nacional Mayor de San Marcos")
        .ciclo("Ciclo VII")
        .gradoAcademico("Bachiller")
        .nivelIngles("Intermedio")
        .experienciaLaboral("Sí")
        .descripcionExperiencia("Desarrollador de software en empresa X")
        .nivelProgramacion("Avanzado")
        .status((short) 1)
        .nivelLenguajes(Arrays.asList(
          NivelEstudianteRequest.builder()
            .idNivel(1L)
            .idLenguaje(1L)
            .build(),
          NivelEstudianteRequest.builder()
            .idNivel(2L)
            .idLenguaje(2L)
            .build()
        ))
        .build();
    }
    return estudianteRequest;
  }
  public static ProgramaEstudiantesRequest getProgramaEstudiantesRequest() {
    if(programaEstudiantesRequest == null){
      List<EstudianteRequest> estudianteRequests = Arrays.asList(
        EstudianteTestUtils.getEstudianteRequest(), EstudianteTestUtils.getEstudianteRequest()
      );
      programaEstudiantesRequest =  ProgramaEstudiantesRequest.builder()
        .idPrograma(1L)
        .estudiantes(estudianteRequests)
        .build();
    }
    return programaEstudiantesRequest;
  }

  public static List<Map<String, Object>> getEstudiantesSqlHashMap() {
    if(estudiantesSqlHashMap == null){
      estudiantesSqlHashMap = new ArrayList<>();
      Map<String, Object> estudiante1  = new HashMap<>();
      estudiante1.put("e_id", 1L);
      estudiante1.put("ne_id", 2L);
      estudiante1.put("ni_id", 3L);
      estudiante1.put("ni_descripcion", "Nivel 1");
      estudiante1.put("pa_id", 4L);
      estudiante1.put("pa_nombre", "Parametro 1");
      estudiante1.put("genero_descripcion", "Masculino");
      estudiante1.put("tipo_documento_descripcion", "DNI");
      Map<String, Object> estudiante2 = new HashMap<>();
      estudiante2.put("e_id", 2L);
      estudiante2.put("ne_id", 1L);
      estudiante2.put("ni_id", null);
      estudiante2.put("pa_id", null);
      estudiante2.put("genero_descripcion", "Femenino");
      estudiante2.put("tipo_documento_descripcion", "Pasaporte");
      Map<String, Object> estudiante3 = new HashMap<>();
      estudiante3.put("e_id", 3L);
      estudiante3.put("ne_id", null);
      estudiante3.put("genero_descripcion", "Femenino");
      estudiante3.put("tipo_documento_descripcion", "Pasaporte");
      estudiantesSqlHashMap.add(estudiante1);
      estudiantesSqlHashMap.add(estudiante2);
      estudiantesSqlHashMap.add(estudiante3);
    }
    return estudiantesSqlHashMap;
  }
}