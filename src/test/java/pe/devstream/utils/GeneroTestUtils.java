package pe.devstream.utils;
import pe.devstream.controller.response.GeneroResponse;
import pe.devstream.repository.model.Genero;

public class GeneroTestUtils {
  private static Genero genero;
  private static GeneroResponse generoResponse;

  public static Genero getGenero()  {
    if (genero == null) {
      genero = Genero.builder()
        .id(1L)
        .descripcion("Genero 1")
        .build();
    }
    return genero;
  }
  public static GeneroResponse getGeneroResponse()  {
    if (generoResponse == null) {
      generoResponse = GeneroResponse.builder()
        .id(1L)
        .descripcion("Genero 1")
        .build();
    }
    return generoResponse;
  }
}
