package pe.devstream.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import pe.devstream.controller.response.EstudianteResponse;
import pe.devstream.repository.EstudianteRepository;
import pe.devstream.repository.model.Estudiante;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class EstudianteRepositoryImpl implements EstudianteRepository {
  private final DatabaseClient databaseClient;
  public static final String LEFTJOIN = "LEFT JOIN ";

  public Flux<EstudianteResponse> getAllEstudiantesPorPrograma(Long id) {
    return databaseClient.sql(
        "SELECT e.*, e.id AS e_id, ne.id AS ne_id, ni.id AS ni_id, ni.descripcion AS ni_descripcion , pa.id AS pa_id , pa.nombre AS pa_nombre , g.descripcion AS genero_descripcion, t.descripcion AS tipo_documento_descripcion " +
          "FROM " + formatTableName("Estudiante") + " e " +
          LEFTJOIN + formatTableName("Genero") + " g ON e.id_genero = g.id " +
          LEFTJOIN + formatTableName("TipoDocumento") + " t ON e.id_tipo_documento = t.id " +
          LEFTJOIN + formatTableName("Nivel_Estudiante")+ " ne ON e.id = ne.id_estudiante " +
          LEFTJOIN + formatTableName("Nivel")+ " ni ON ne.id_nivel = ni.id " +
          LEFTJOIN + formatTableName("Parametros")+ " pa ON ne.id_parametros = pa.id " +
          LEFTJOIN + formatTableName("Programa_Estudiante")+ " pe ON e.id = pe.id_estudiante " +
          "WHERE pe.id_programa = :id ")
      .bind("id", id)
      .fetch()
      .all()
      .bufferUntilChanged(result -> result.get("e_id"))
      .flatMap(Estudiante::fromRows);
  }
  private String formatTableName(String tableName){
    return "\"public\".\"" + tableName + "\"";
  }
}