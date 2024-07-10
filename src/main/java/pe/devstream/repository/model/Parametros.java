package pe.devstream.repository.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import pe.devstream.controller.response.NivelReponse;
import pe.devstream.controller.response.ParametrosReponse;

import java.sql.Timestamp;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("\"Parametros\"")
public class Parametros {

  @Id
  private Long id;

  @Column("nombre")
  private String nombre;

  @Column("descripcion")
  private String descripcion;

  @Column("valor")
  private String valor;

  @Column("parent")
  private Long parent;

  @Column("created_by")
  private String createdBy;

  @Column("created_at")
  private Timestamp createdAt;

  @Column("modify_by")
  private String modifyBy;

  @Column("modify_at")
  private Timestamp modifyAt;

  @Column("status")
  private Short status;

  public static ParametrosReponse fromRow(Map<String, Object> row) {
    if (row.get("pa_id") != null) {
      return ParametrosReponse.builder()
        .id((Long.parseLong(row.get("pa_id").toString())))
        .nombre((String) row.get("pa_nombre"))
        .build();
    } else {
      return null;
    }
  }

}