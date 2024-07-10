package pe.devstream.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NivelEstudianteRequest {
  private Long idNivel;
  private Long idLenguaje;
}
