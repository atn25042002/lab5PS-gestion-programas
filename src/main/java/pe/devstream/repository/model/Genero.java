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
@Table("\"Genero\"")
public class Genero {
  @Id
  public Long id;
  @Column("descripcion")
  public String descripcion;
}

