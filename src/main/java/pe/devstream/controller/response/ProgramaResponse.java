package pe.devstream.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramaResponse {
  private Long id;
  private String descripcion;
  private String nombre;
  private Timestamp fechaInicio;
  private Timestamp fechaFin;
  private Timestamp fechaInicioReal;
  private Timestamp fechaFinReal;
}
