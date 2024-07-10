package pe.devstream.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramaEstudiantesRequest {
  private Long idPrograma;
  private List<EstudianteRequest> estudiantes;
}
