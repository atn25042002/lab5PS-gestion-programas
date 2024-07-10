package pe.devstream.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pe.devstream.controller.validation.constantes.Constantes;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@ToString
public class TipoErrorResponse implements Serializable {

  @Schema(description = Constantes.TIPO_ERROR_DESCRIPTION, example = Constantes.TIPO_ERROR_EXAMPLE)
  private String tipo;

  @Schema(description = Constantes.CODIGO_ERROR_DESCRIPTION, example = Constantes.CODIGO_ERROR_EXAMPLE)
  private String codigo;

  @Schema(description = Constantes.MENSAJE_ERROR_DESCRIPTION, example = Constantes.MENSAJE_ERROR_EXAMPLE)
  private String mensaje;
}
