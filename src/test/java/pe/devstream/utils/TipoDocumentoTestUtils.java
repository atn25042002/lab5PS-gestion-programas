package pe.devstream.utils;

import pe.devstream.controller.response.TipoDocumentoResponse;
import pe.devstream.repository.model.TipoDocumento;

public class TipoDocumentoTestUtils {
  private static TipoDocumento tipoDocumento;
  private static TipoDocumentoResponse tipoDocumentoResponse;

  public static TipoDocumento getTipoDocumento() {
    if(tipoDocumento == null){
      tipoDocumento = TipoDocumento.builder()
        .id(1L)
        .descripcion("TipoDocumento 1")
        .build();
    }
    return tipoDocumento;
  }

  public static TipoDocumentoResponse getTipoDocumentoResponse() {
    if(tipoDocumentoResponse == null){
      tipoDocumentoResponse = TipoDocumentoResponse.builder()
        .id(1L)
        .descripcion("TipoDocumento 1")
        .build();
    }
    return tipoDocumentoResponse;
  }
}
