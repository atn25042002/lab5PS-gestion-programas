package pe.devstream.controller.response;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaDatosResponse {
    Long id;
    String nombre;
    String descripcion;
    List<LeccionDatosResponse> lecciones;

    /*public static Mono<List<CategoriaDatosResponse>> fromRows(List<Map<String, Object>> rows) {
        Map<Long, List<Map<String, Object>>> leccionesGroupedByIdCategoria = rows.stream()
                .collect(Collectors.groupingBy(row -> (Long) row.get("id_categoria")));

        List<CategoriaDatosResponse> categoriasResponses = leccionesGroupedByIdCategoria.entrySet().stream()
                .map(entry -> {
                    Long idCategoria = entry.getKey();
                    List<Map<String, Object>> leccionesRows = entry.getValue();
                    List<LeccionDatosResponse> lecciones = leccionesRows.stream()
                            .map(leccionRow -> {
                                Long idLeccion = (Long) leccionRow.get("id_leccion");
                                String nombreLeccion = (String) leccionRow.get("leccion_nombre");
                                List<ClaseDatosResponse> clases = leccionesRows.stream()
                                        .filter(claseRow -> idLeccion.equals(claseRow.get("id_leccion")))
                                        .map(claseRow -> ClaseDatosResponse.builder()
                                                .id((Long) claseRow.get("id"))
                                                .nombre((String) claseRow.get("clase_nombre"))
                                                .anotaciones((String) claseRow.get("clase_anotaciones"))
                                                .status((Short) claseRow.get("status"))
                                                .isFinished((Short) claseRow.get("is_finished"))
                                                .build())
                                        .collect(Collectors.toList());

                                return new LeccionDatosResponse(idLeccion, nombreLeccion, clases);
                            })
                            .collect(Collectors.toList());

                    String nombreCategoria = (String) leccionesRows.get(0).get("categoria_nombre");
                    String descripcionCategoria = (String) leccionesRows.get(0).get("categoria_descripcion");

                    return new CategoriaDatosResponse(idCategoria, nombreCategoria, descripcionCategoria, lecciones);
                })
                .collect(Collectors.toList());

        return Mono.just(categoriasResponses);
    }*/

    public static Flux<CategoriaDatosResponse> fromRows(List<Map<String, Object>> rows) {
        Map<Long, List<Map<String, Object>>> leccionesGroupedByIdCategoria = rows.stream()
                .collect(Collectors.groupingBy(row -> (Long) row.get("id_categoria")));

        List<CategoriaDatosResponse> categoriasResponses = leccionesGroupedByIdCategoria.entrySet().stream()
                .map(entry -> {
                    Long idCategoria = entry.getKey();
                    List<Map<String, Object>> leccionesRows = entry.getValue();
                    List<LeccionDatosResponse> lecciones = leccionesRows.stream()
                            .map(leccionRow -> {
                                Long idLeccion = (Long) leccionRow.get("id_leccion");
                                String nombreLeccion = (String) leccionRow.get("leccion_nombre");
                                List<ClaseDatosResponse> clases = leccionesRows.stream()
                                        .filter(claseRow -> idLeccion.equals(claseRow.get("id_leccion")))
                                        .map(claseRow -> ClaseDatosResponse.builder()
                                                .id((Long) claseRow.get("id"))
                                                .nombre((String) claseRow.get("clase_nombre"))
                                                .anotaciones((String) claseRow.get("clase_anotaciones"))
                                                .status((Short) claseRow.get("status"))
                                                .isFinished((Long) claseRow.get("is_finished"))
                                                .build())
                                        .collect(Collectors.toList());

                                return new LeccionDatosResponse(idLeccion, nombreLeccion, clases);
                            })
                            .collect(Collectors.toList());

                    String nombreCategoria = (String) leccionesRows.get(0).get("categoria_nombre");
                    String descripcionCategoria = (String) leccionesRows.get(0).get("categoria_descripcion");

                    return new CategoriaDatosResponse(idCategoria, nombreCategoria, descripcionCategoria, lecciones);
                })
                .collect(Collectors.toList());

        return Flux.fromIterable(categoriasResponses);
    }
}
