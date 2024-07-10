package pe.devstream.repository.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("\"Programa\"")
public class Programa {
  @Id
  @Column("id")
  private Long id;

  @Column("descripcion")
  private String descripcion;

  @Column("nombre")
  private String nombre;

  @Column("fecha_inicio")
  private Timestamp fechaInicio;

  @Column("fecha_fin")
  private Timestamp fechaFin;

  @Column("fecha_inicio_real")
  private Timestamp fechaInicioReal;

  @Column("fecha_fin_real")
  private Timestamp fechaFinReal;

}