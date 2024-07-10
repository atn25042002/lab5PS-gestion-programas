package pe.devstream.repository.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.devstream.controller.response.ProgramaClaseResponse;
import reactor.core.publisher.Mono;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;
import java.util.List;
import java.util.Map;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("\"Programa_Clase\"")
public class ProgramaClase {

  @Id
  @Column("id")
  private Long id;

  @Column("created_by")
  private String createdBy;

  @Column("created_at")
  private Date createdAt;

  @Column("modify_by")
  private String modifyBy;

  @Column("modify_at")
  private Date modifyAt;

  @Column("status")
  private Short status;

  @Column("id_programa")
  private Long idPrograma;

  @Column("is_finished")
  private Long isFinished;

  @Column("id_clase")
  private Long idClase;

  public static Mono<ProgramaClaseResponse> fromRows(List<Map<String, Object>> rows) {
    return Mono.just(ProgramaClaseResponse.builder()
      .id((Long.parseLong(rows.get(0).get("id").toString())))
      .status((Short) rows.get(0).get("status"))
      .isFinished((Long.parseLong(rows.get(0).get("is_finished").toString())))
      .claseNombre((String) rows.get(0).get("clase_nombre"))
      .claseAnotaciones((String) rows.get(0).get("clase_anotaciones"))
      .idLeccion((Long.parseLong(rows.get(0).get("id_leccion").toString())))
      .leccionNombre((String) rows.get(0).get("leccion_nombre"))
      .idCategoria((Long.parseLong(rows.get(0).get("id_categoria").toString())))
      .categoriaNombre((String) rows.get(0).get("categoria_nombre"))
      .categoriaDescripcion((String) rows.get(0).get("categoria_descripcion"))
      .build());
  }
}