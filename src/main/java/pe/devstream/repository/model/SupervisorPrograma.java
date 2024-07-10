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
@Table("\"Supervisor_Programa\"")
public class SupervisorPrograma {
  @Id
  @Column("id")
  private Long id;

  @Column("id_programa")
  private Long idPrograma;

  @Column("correo")
  private String correo;

  @Column("nombre")
  private String nombre;

  @Column("status")
  private Short status;

}