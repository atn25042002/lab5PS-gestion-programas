package pe.devstream.controller.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClaseDatosResponse {
    Long id;
    String nombre;
    String anotaciones;
    Short status;
    Long isFinished;
}
