package pe.devstream.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import pe.devstream.controller.response.NivelEstudianteResponse;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("\"Nivel_Estudiante\"")
public class NivelEstudiante {

  @Id
  private Long id;

  @Column("id_nivel")
  private Long idNivel;

  @Column("id_estudiante")
  private Long idEstudiante;

  @Column("id_parametros")
  private Long idParametros;

  public static NivelEstudianteResponse fromRow(Map<String, Object> row) {
    if (row.get("ne_id") != null) {
      return NivelEstudianteResponse.builder()
        .id((Long.parseLong(row.get("ne_id").toString())))
        .nivel(Nivel.fromRow(row))
        .parametros(Parametros.fromRow(row))
        .build();
    } else {
      return null;
    }

  }


}
