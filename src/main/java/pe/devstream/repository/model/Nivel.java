package pe.devstream.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import pe.devstream.controller.response.NivelReponse;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("\"Nivel\"")
public class Nivel {
  @Id
  private Long id;

  @Column("descripcion")
  private String descripcion;
  public static NivelReponse fromRow(Map<String, Object> row) {
    if (row.get("ni_id") != null) {
      return NivelReponse.builder()
        .id((Long.parseLong(row.get("ni_id").toString())))
        .descripcion((String) row.get("ni_descripcion"))
        .build();
    } else {
      return null;
    }
  }
}
