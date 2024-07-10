package pe.devstream.controller.response;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeccionDatosResponse {
    Long id;
    String nombre;
    List<ClaseDatosResponse> clases;
}
