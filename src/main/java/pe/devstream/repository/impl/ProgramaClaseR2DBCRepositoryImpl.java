package pe.devstream.repository.impl;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.r2dbc.core.DatabaseClient;

import lombok.RequiredArgsConstructor;
import pe.devstream.controller.response.CategoriaDatosResponse;
import pe.devstream.controller.response.ClaseDatosResponse;
import pe.devstream.controller.response.LeccionDatosResponse;
import pe.devstream.controller.response.ProgramaClaseResponse;
import pe.devstream.controller.response.ProgramaDatosResponse;
import pe.devstream.repository.ProgramaClaseR2DBCRepository;
import pe.devstream.repository.model.ProgramaClase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ProgramaClaseR2DBCRepositoryImpl implements ProgramaClaseR2DBCRepository {
    private final DatabaseClient databaseClient;

    public Flux<CategoriaDatosResponse> getAllClasesPorPrograma(Long id_programa) {
        return databaseClient.sql(
                "SELECT pc.id AS id, pc.status AS status, pc.is_finished AS is_finished, c.nombre AS clase_nombre, c.anotaciones AS clase_anotaciones, c.leccion_id AS id_leccion, l.nombre AS leccion_nombre, l.id_categoria AS id_categoria, ct.nombre AS categoria_nombre, ct.descripcion AS categoria_descripcion"
                        +
                        " FROM " + formatTableName("Programa_Clase") + " pc" +
                        " INNER JOIN " + formatTableName("Clase") + " c ON pc.id_clase = c.id" +
                        " INNER JOIN " + formatTableName("Leccion") + " l ON c.leccion_id = l.id" +
                        " INNER JOIN " + formatTableName("Categoria") + " ct ON l.id_categoria = ct.id" +
                        " WHERE pc.id_programa = :id " +
                        " ORDER BY l.id_categoria, c.leccion_id;")
                .bind("id", id_programa)
                .fetch()
                .all()
                .collectList() // Agrupa los resultados en una lista
                .flatMapMany(rows -> {
                    // Procesa la lista de resultados agrupados para construir la estructura deseada
                    Map<Long, CategoriaDatosResponse> categoriasMap = new HashMap<>();

                    for (Map<String, Object> row : rows) {
                        Long idCategoria = (Long) row.get("id_categoria");

                        CategoriaDatosResponse categoria = categoriasMap.get(idCategoria);
                        if (categoria == null) {
                            categoria = new CategoriaDatosResponse();
                            categoria.setId(idCategoria);
                            categoria.setNombre((String) row.get("categoria_nombre"));
                            categoria.setDescripcion((String) row.get("categoria_descripcion"));
                            categoria.setLecciones(new ArrayList<>());
                            categoriasMap.put(idCategoria, categoria);
                        }

                        Long idLeccion = (Long) row.get("id_leccion");
                        LeccionDatosResponse leccion = categoria.getLecciones().stream()
                                .filter(l -> l.getId().equals(idLeccion))
                                .findFirst()
                                .orElse(null);

                        if (leccion == null) {
                            leccion = new LeccionDatosResponse();
                            leccion.setId(idLeccion);
                            leccion.setNombre((String) row.get("leccion_nombre"));
                            leccion.setClases(new ArrayList<>());
                            categoria.getLecciones().add(leccion);
                        }

                        ClaseDatosResponse clase = new ClaseDatosResponse();
                        clase.setId((Long) row.get("id"));
                        clase.setNombre((String) row.get("clase_nombre"));
                        clase.setAnotaciones((String) row.get("clase_anotaciones"));
                        clase.setStatus((Short) row.get("status"));
                        clase.setIsFinished((Long) row.get("is_finished"));

                        leccion.getClases().add(clase);
                    }

                    return Flux.fromIterable(new ArrayList<>(categoriasMap.values()));
                });
    }

    public Mono<ProgramaDatosResponse> getNombrePrograma(Long id_programa) {
        return databaseClient.sql("SELECT p.id AS id, p.nombre AS nombre " +
                " FROM " + formatTableName("Programa") + " p" +
                " WHERE p.id = :id ;")
                .bind("id", id_programa)
                .fetch()
                .one()
                .map(row -> new ProgramaDatosResponse((Long) row.get("id"),
                        (String) row.get("nombre")));
    }

    public Flux<ProgramaClaseResponse> getAllProgramaClases() {
        return databaseClient.sql(
                "SELECT pc.id AS id, pc.status AS status, pc.is_finished AS is_finished, c.nombre AS clase_nombre, c.leccion_id AS id_leccion, l.id_categoria AS id_categoria"
                        +
                        " FROM " + formatTableName("Programa_Clase") + " pc" +
                        " INNER JOIN " + formatTableName("Clase") + " c ON pc.id_clase = c.id" +
                        " INNER JOIN " + formatTableName("Leccion") + " l ON c.leccion_id = l.id" +
                        " ORDER BY l.id_categoria, c.leccion_id;")
                .fetch()
                .all()
                .bufferUntilChanged(result -> result.get("id"))
                .flatMap(ProgramaClase::fromRows);
    }

    private String formatTableName(String table_name) {
        return "\"" + table_name + "\"";
    }
}
