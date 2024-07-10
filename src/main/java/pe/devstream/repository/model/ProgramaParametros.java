package pe.devstream.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("\"Programa_Parametros\"")
public class ProgramaParametros {
  @Id
  @Column("id")
  private Long id;
  @Column("id_programa")
  private Long idPrograma;
  @Column("id_parametros")
  private Long idParametros;
}
