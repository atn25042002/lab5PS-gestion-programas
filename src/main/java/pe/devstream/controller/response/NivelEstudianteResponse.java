package pe.devstream.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NivelEstudianteResponse {
  private Long id;
  private NivelReponse nivel;
  private ParametrosReponse parametros;
}
