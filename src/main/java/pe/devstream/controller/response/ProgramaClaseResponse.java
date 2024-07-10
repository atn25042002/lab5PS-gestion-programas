package pe.devstream.controller.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramaClaseResponse {
  private Long id;
  private Short status;
  private Long isFinished;
  private String claseNombre;
  private String claseAnotaciones;
  private Long idLeccion;
  private String leccionNombre;
  private Long idCategoria;
  private String categoriaNombre;
  private String categoriaDescripcion;
}
