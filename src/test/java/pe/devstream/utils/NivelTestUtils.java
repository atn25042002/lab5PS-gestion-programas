package pe.devstream.utils;

import pe.devstream.controller.response.NivelReponse;
import pe.devstream.repository.model.Nivel;
public class NivelTestUtils {
  private static Nivel nivel;
  private static NivelReponse nivelReponse;

  public static Nivel getNivel() {
    if(nivel == null){
      nivel =  Nivel.builder()
        .id(1L)
        .descripcion("Nivel 1")
        .build();
    }
    return nivel;
  }

  public static NivelReponse getNivelReponse() {
    if(nivelReponse == null){
      nivelReponse = NivelReponse.builder()
        .id(1L)
        .descripcion("Nivel 1")
        .build();
    }
    return nivelReponse;
  }
}
