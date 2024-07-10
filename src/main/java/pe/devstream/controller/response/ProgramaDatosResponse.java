package pe.devstream.controller.response;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramaDatosResponse {
    Long id;
    String nombre;

    public static Mono<ProgramaDatosResponse> fromRows(List<Map<String, Object>> rows) {
        return Mono.just(ProgramaDatosResponse.builder()
          .id((Long.parseLong(rows.get(0).get("id").toString())))
          .nombre((String) rows.get(0).get("nombre"))
          .build());
      }
}
