package pe.devstream.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.devstream.controller.request.HeaderRequest;
import pe.devstream.controller.response.CategoriaDatosResponse;
import pe.devstream.controller.response.ErrorResponse;
import pe.devstream.controller.response.ProgramaClaseResponse;
import pe.devstream.controller.response.ProgramaDatosResponse;
import pe.devstream.controller.validation.constantes.Constantes;
import pe.devstream.controller.validation.constraint.ApplicationNameConstraint;
import pe.devstream.controller.validation.constraint.TransactionIdConstraint;
import pe.devstream.controller.validation.constraint.UserConsumerIdConstraint;
import pe.devstream.controller.wrapper.WrapperGenericoLista;
import pe.devstream.controller.wrapper.WrapperGenericoObjetos;
import pe.devstream.repository.model.ProgramaClase;
import pe.devstream.service.ProgramaClaseService;
import reactor.core.publisher.Mono;

@CrossOrigin
@RequestMapping
@RequiredArgsConstructor
@RestController
@Validated
public class ProgramaClaseController {

  private final ProgramaClaseService programaClaseService;

  @GetMapping("/listar-programa-clase")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
    summary = "Listar programa-clase",
    description = "Endpoint para listar las relaciones entre clases y programas",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "Lista de programa-clase generado exitosamente",
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
    }
  )
  public Mono<WrapperGenericoLista<ProgramaClaseResponse>> listarProgramaClase(
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
    return programaClaseService.listarProgramasClase().map(WrapperGenericoLista::new);
  }
  
  @GetMapping("/programa-clase/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
    summary = "Obtener Programas Clases segun idPrograma",
    description = "Endpoint para obtener las clases de un programa",
    parameters = {
        @Parameter(
            name = "idPrograma",
            description = "ID del programa",
            required = true,
            schema = @Schema(type = "long")
        )
    },
    responses = {
        @ApiResponse(
          responseCode = "200",
          description = "Lista de clases generada exitosamente",
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
    }
  )
  public Mono<WrapperGenericoLista<CategoriaDatosResponse>> listarClases(
          @PathVariable Long id,
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
    return programaClaseService.listarClases(id).map(WrapperGenericoLista::new);
  }

  @GetMapping("/programa-clase/programa/{id}")
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
  public Mono<Object> getDatosPrograma(
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
    return programaClaseService.datosPrograma(id).map(WrapperGenericoObjetos::new);
  }
}
