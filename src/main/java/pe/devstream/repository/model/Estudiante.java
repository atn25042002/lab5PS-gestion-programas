package pe.devstream.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import pe.devstream.controller.response.EstudianteResponse;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("\"Estudiante\"")
public class Estudiante {

  @Id
  private Long id;

  @Column("usuario")
  private String usuario;

  @Column("contraseña")
  private String contrasena;

  @Column("id_tipo_documento")
  private Long idTipoDocumento;

  @Column("nro_documento")
  private String nroDocumento;

  @Column("nombres_apellidos")
  private String nombresApellidos;

  @Column("id_genero")
  private Long idGenero;

  @Column("fecha_nacimiento")
  private String fechaNacimiento;

  @Column("correo")
  private String correo;

  @Column("nro_celular")
  private String nroCelular;

  @Column("puntaje_pseudocodigo")
  private Double puntajePseudocodigo;

  @Column("puntaje_razonamiento_math")
  private Double puntajeRazonamientoMath;

  @Column("cooperativo")
  private String cooperativo;

  @Column("facilidad_palabra")
  private String facilidadPalabra;

  @Column("lider")
  private String lider;

  @Column("ingenioso")
  private String ingenioso;

  @Column("organizado")
  private String organizado;

  @Column("confrontacional")
  private String confrontacional;

  @Column("aislado")
  private String aislado;

  @Column("no_empatico")
  private String noEmpatico;

  @Column("pais")
  private String pais;

  @Column("provincia")
  private String provincia;

  @Column("carrera")
  private String carrera;

  @Column("universidad")
  private String universidad;

  @Column("ciclo")
  private String ciclo;

  @Column("grado_academico")
  private String gradoAcademico;

  @Column("nivel_ingles")
  private String nivelIngles;

  @Column("experiencia_laboral")
  private String experienciaLaboral;

  @Column("descripcion_experiencia")
  private String descripcionExperiencia;

  @Column("nivel_programacion")
  private String nivelProgramacion;

  @Column("status")
  private Short status;

  @Column("created_by")
  private String createdBy;

  @Column("created_at")
  private Timestamp createdAt;

  @Column("modify_by")
  private String modifyBy;

  @Column("modify_at")
  private Timestamp modifyAt;

  public static Mono<EstudianteResponse> fromRows(List<Map<String, Object>> rows) {
    return Mono.just(EstudianteResponse.builder()
      .id((Long.parseLong(rows.get(0).get("e_id").toString())))
      .tipoDocumento((String) rows.get(0).get("tipo_documento_descripcion"))
      .nroDocumento((String) rows.get(0).get("nro_documento"))
      .nombresApellidos((String) rows.get(0).get("nombres_apellidos"))
      .genero((String) rows.get(0).get("genero_descripcion"))
      .fechaNacimiento((String) rows.get(0).get("fecha_nacimiento"))
      .correo((String) rows.get(0).get("correo"))
      .nroCelular((String) rows.get(0).get("nro_celular"))
      .puntajePseudocodigo((Double) rows.get(0).get("puntaje_pseudocodigo"))
      .puntajeRazonamientoMath((Double) rows.get(0).get("puntaje_razonamiento_math"))
      .cooperativo((String) rows.get(0).get("cooperativo"))
      .facilidadPalabra((String) rows.get(0).get("facilidad_palabra"))
      .lider((String) rows.get(0).get("lider"))
      .ingenioso((String) rows.get(0).get("ingenioso"))
      .organizado((String) rows.get(0).get("organizado"))
      .confrontacional((String) rows.get(0).get("confrontacional"))
      .aislado((String) rows.get(0).get("aislado"))
      .noEmpatico((String) rows.get(0).get("no_empatico"))
      .pais((String) rows.get(0).get("pais"))
      .provincia((String) rows.get(0).get("provincia"))
      .carrera((String) rows.get(0).get("carrera"))
      .universidad((String) rows.get(0).get("universidad"))
      .ciclo((String) rows.get(0).get("ciclo"))
      .gradoAcademico((String) rows.get(0).get("grado_academico"))
      .nivelIngles((String) rows.get(0).get("nivel_ingles"))
      .experienciaLaboral((String) rows.get(0).get("experiencia_laboral"))
      .descripcionExperiencia((String) rows.get(0).get("descripcion_experiencia"))
      .nivelProgramacion((String) rows.get(0).get("nivel_programacion"))
      .status((Short) rows.get(0).get("status"))
      .nivelParametros(rows.stream()
        .map(NivelEstudiante::fromRow)
        .filter(Objects::nonNull)
        .toList())
      .build());
  }

}
