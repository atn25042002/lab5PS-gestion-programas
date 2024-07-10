package pe.devstream.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.StringToClassMapItem;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.devstream.controller.request.HeaderRequest;
import pe.devstream.controller.request.ProgramaEstudiantesRequest;
import pe.devstream.controller.response.ErrorResponse;
import pe.devstream.controller.response.EstudianteResponse;
import pe.devstream.controller.validation.constantes.Constantes;
import pe.devstream.controller.validation.constraint.ApplicationNameConstraint;
import pe.devstream.controller.validation.constraint.TransactionIdConstraint;
import pe.devstream.controller.validation.constraint.UserConsumerIdConstraint;
import pe.devstream.controller.wrapper.WrapperGenericoLista;
import pe.devstream.controller.wrapper.WrapperGenericoObjetos;
import pe.devstream.service.EstudianteService;
import reactor.core.publisher.Mono;

@CrossOrigin
@RequestMapping
@RequiredArgsConstructor
@RestController
@Slf4j
@Validated
public class EstudianteController {

  private final EstudianteService estudianteService;

  @GetMapping("/listar-estudiante-programa/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
    summary = "Listar estudiante excel",
    description = "Endpoint para listar todos los estudiante excel.",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "Lista de  estudiante excel generado exitosamente",
        content = @Content(mediaType = "application/json")
      ),
      @ApiResponse(
        responseCode = "400",
        description = "Solicitud incorrecta",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
      ),
      @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
      ),
    }
  )
  public Mono<WrapperGenericoLista<EstudianteResponse>> listarEstudiantePrograma(
    @Parameter(description = Constantes.TRANSACTION_ID_DESCRIPTION, example = Constantes.TRANSACTION_ID_EXAMPLE)
    @TransactionIdConstraint @RequestHeader(value = Constantes.TRANSACTION_ID) String idTransaction,

    @Parameter(description = Constantes.APPLICATION_NAME_DESCRIPTION, example = Constantes.APPLICATION_NAME_EXAMPLE)
    @ApplicationNameConstraint @RequestHeader(value = Constantes.APPLICATION_NAME) String nameApplication,

    @Parameter(description = Constantes.USER_CONSUMER_ID_DESCRIPTION, example = Constantes.USER_CONSUMER_ID_EXAMPLE)
    @UserConsumerIdConstraint @RequestHeader(value = Constantes.USER_CONSUMER_ID) String userConsumerId,
    @PathVariable Long id
  ) {
    HeaderRequest headerRequest = HeaderRequest.builder()
      .transactionId(idTransaction)
      .applicationName(nameApplication)
      .userConsumerId(userConsumerId)
      .build();
    log.info("Listar estudiantes Programa Request, HeaderRequest: {}", headerRequest.toString());
    return estudianteService.listarEstudiantePorPrograma(id).map(WrapperGenericoLista::new);
  }
  @PostMapping("/crear-estudiante-programa-masivo")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
    summary = "Crear estudiantes por programa masivo",
    description = "Endpoint para crear estudiantes por programa masivo.",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "Creacion de  estudiantes",
        content = @Content(mediaType = "application/json")
      ),
      @ApiResponse(
        responseCode = "400",
        description = "Solicitud incorrecta",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
      ),
      @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
      )
    },
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Solicitud para crear un estudiantes masivo",
      content = @Content(mediaType = "application/json", schema = @Schema(type = "object", properties = {
        @StringToClassMapItem(key = "datos", value = ProgramaEstudiantesRequest.class)
      }))
    )
  )
  public Mono<WrapperGenericoObjetos<Object>> guardarEstudiantes(
    @RequestBody WrapperGenericoObjetos<ProgramaEstudiantesRequest> programaEstudiantes,
    @Parameter(description = Constantes.TRANSACTION_ID_DESCRIPTION, example = Constantes.TRANSACTION_ID_EXAMPLE)
    @TransactionIdConstraint @RequestHeader(value = Constantes.TRANSACTION_ID) String idTransaction,

    @Parameter(description = Constantes.APPLICATION_NAME_DESCRIPTION, example = Constantes.APPLICATION_NAME_EXAMPLE)
    @ApplicationNameConstraint @RequestHeader(value = Constantes.APPLICATION_NAME) String nameApplication,

    @Parameter(description = Constantes.USER_CONSUMER_ID_DESCRIPTION, example = Constantes.USER_CONSUMER_ID_EXAMPLE)
    @UserConsumerIdConstraint @RequestHeader(value = Constantes.USER_CONSUMER_ID) String userConsumerId
  ) {
    HeaderRequest headerRequest = HeaderRequest.builder()
      .transactionId(idTransaction)
      .applicationName(nameApplication)
      .userConsumerId(userConsumerId)
      .build();
    log.info("Guardar Estudiante Request, HeaderRequest: {}", headerRequest.toString());
    return estudianteService.guardarEstudiantes(programaEstudiantes.getDatos()).map(WrapperGenericoObjetos::new);
  }

}