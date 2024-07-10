package pe.devstream.controller.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
@ToString
public class ErrorResponse implements Serializable {
  private TipoErrorResponse error;

  public ErrorResponse buildErrorResponse(String tipo, String code, String message) {
    return ErrorResponse.builder()
            .error(TipoErrorResponse.builder()
                    .tipo(tipo)
                    .codigo(code)
                    .mensaje(message)
                    .build()
            ).build();
  }

  public String returnCodigoError(String tipoError, String tipoCanal, String componente, String numeroError) {
    return tipoError + tipoCanal + "-" + componente + "-" + numeroError;
  }
}
