package pe.devstream.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstudianteResponse  {

  private Long id;
  private String tipoDocumento;
  private String nroDocumento;
  private String nombresApellidos;
  private String genero;
  private String fechaNacimiento;
  private String correo;
  private String nroCelular;
  private Double puntajePseudocodigo;
  private Double puntajeRazonamientoMath;
  private String cooperativo;
  private String facilidadPalabra;
  private String lider;
  private String ingenioso;
  private String organizado;
  private String confrontacional;
  private String aislado;
  private String noEmpatico;
  private String pais;
  private String provincia;
  private String carrera;
  private String universidad;
  private String ciclo;
  private String gradoAcademico;
  private String nivelIngles;
  private String experienciaLaboral;
  private String descripcionExperiencia;
  private String nivelProgramacion;
  private Short status;
  private List<NivelEstudianteResponse> nivelParametros;

}