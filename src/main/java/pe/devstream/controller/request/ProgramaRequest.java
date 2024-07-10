package pe.devstream.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramaRequest {
  private String descripcion;
  private String nombre;
  private String createdBy;
  private Timestamp fechaInicio;
  private Timestamp fechaFin;
  private List<SupervisorRequest> supervisores;
  private List<MentorRequest> mentores;
  private List<Long> herramientas;
  private List<Long> clases;
}
