package pe.devstream.utils;

import java.time.LocalDate;

import pe.devstream.controller.response.ProgramaClaseResponse;
import pe.devstream.repository.model.ProgramaClase;

public class ProgramaClaseTestUtils {
    private static ProgramaClase programaClase;
    private static ProgramaClaseResponse programaClaseResponse;

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

    public static ProgramaClaseResponse getProgramaClaseResponse(){
        if (programaClaseResponse == null) {
            programaClaseResponse = ProgramaClaseResponse.builder()
              .id(1L)
              .status((short) 1)
              .isFinished(0L)
              .build();
          }
        return programaClaseResponse;
    }
}
