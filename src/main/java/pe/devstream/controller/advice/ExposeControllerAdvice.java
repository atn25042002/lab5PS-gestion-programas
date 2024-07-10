package pe.devstream.controller.advice;

import static pe.devstream.controller.validation.constantes.Mensajes.FORMAT_NOT_REQUIRED;
import static pe.devstream.controller.validation.constantes.Mensajes.NOT_NULL;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.server.ServerWebInputException;
import pe.devstream.controller.response.ErrorResponse;
import pe.devstream.service.exception.EstudiantesDuplicatedException;
import pe.devstream.service.exception.ProgramNameAlreadyTakenException;

@RestControllerAdvice
@Slf4j
public class ExposeControllerAdvice {
  private final ErrorResponse buildErrorResponse = new ErrorResponse();

  @ExceptionHandler(value = {ServerWebInputException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleWebInputException(ServerWebInputException exception) {

    log.error(this.getClass().getSimpleName(), exception);

    MethodParameter methodParam = exception.getMethodParameter();

    String message;

    if (Objects.requireNonNull(methodParam).hasParameterAnnotation(RequestHeader.class)) {
      RequestHeader requestHeader = methodParam.getParameterAnnotation(RequestHeader.class);
      String headerName = requestHeader != null ? requestHeader.value() : "Unknown Header";
      message = headerName + FORMAT_NOT_REQUIRED + NOT_NULL;
    } else {
      message = "Atributo faltante o inválido en la URL: " + methodParam.getParameterName();
    }
    String code = buildErrorResponse.returnCodigoError(
            DetalleErrorEnum.PREFIJO_ERROR_FUNCIONAL.getCode(),
            DetalleErrorEnum.PREFIJO_ERROR_CANAL_NEGOCIO.getCode(),
            DetalleErrorEnum.PREFIJO_ERROR_COMPONENTE_MICROSERVICIO.getCode(),
            DetalleErrorEnum.ERROR_FORMATO_CABECERA.getCode());

    return buildErrorResponse.buildErrorResponse(DetalleErrorEnum.FUNCIONAL.getCode(), code, message);
  }

  @ExceptionHandler(value = {ConstraintViolationException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleWebExchangeBindException(ConstraintViolationException exception) {

    log.error(this.getClass().getSimpleName(), exception);

    String code = buildErrorResponse.returnCodigoError(
            DetalleErrorEnum.PREFIJO_ERROR_FUNCIONAL.getCode(),
            DetalleErrorEnum.PREFIJO_ERROR_CANAL_NEGOCIO.getCode(),
            DetalleErrorEnum.PREFIJO_ERROR_COMPONENTE_MICROSERVICIO.getCode(),
            DetalleErrorEnum.ERROR_VALIDACION_CABECERA.getCode());

    List<ConstraintViolation<?>> fieldErrors = new ArrayList<>(exception.getConstraintViolations());

    String message = String.format("Debe de asignar valor(es) que cumplan con el formato solicitado en el/los siguiente(s) header(s): %s",
            fieldErrors.stream()
                    .map(fieldError -> String.format("%s", fieldError.getMessage()))
                    .collect(Collectors.joining(", ")));

    return buildErrorResponse.buildErrorResponse(DetalleErrorEnum.FUNCIONAL.getCode(), code, message);
  }

  /**
   * This method is used to get only one ErrorResponse.
   * @param exception This is the first paramter to method.
   * @return one ErrorResponse.
   */
  @ExceptionHandler(value = {WebExchangeBindException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleWebExchangeBindException(WebExchangeBindException exception) {

    log.error(this.getClass().getSimpleName(), exception);

    String messageValidate;
    StringBuilder messageFormat = new StringBuilder().append("Debe de asignar valor(es) para el/los atributos:");

    String codeFormat = buildErrorResponse.returnCodigoError(
            DetalleErrorEnum.PREFIJO_ERROR_FUNCIONAL.getCode(),
            DetalleErrorEnum.PREFIJO_ERROR_CANAL_NEGOCIO.getCode(),
            DetalleErrorEnum.PREFIJO_ERROR_COMPONENTE_MICROSERVICIO.getCode(),
            DetalleErrorEnum.ERROR_FORMATO_REQUEST.getCode());

    String codeValidate = buildErrorResponse.returnCodigoError(
            DetalleErrorEnum.PREFIJO_ERROR_FUNCIONAL.getCode(),
            DetalleErrorEnum.PREFIJO_ERROR_CANAL_NEGOCIO.getCode(),
            DetalleErrorEnum.PREFIJO_ERROR_COMPONENTE_MICROSERVICIO.getCode(),
            DetalleErrorEnum.ERROR_VALIDACION_REQUEST.getCode());

    List<ObjectError> objectErrorList = exception.getAllErrors();

    boolean isNullFieldErrorPresent = true;

    for (ObjectError error : objectErrorList) {
      if (error instanceof FieldError fieldError && (Objects.equals("NotNull", fieldError.getCode()))) {
          messageFormat.append(" ").append(fieldError.getField()).append(",");
          isNullFieldErrorPresent = false;

      }
    }

    messageValidate = String.format("Debe de asignar valor(es) que cumplan con el formato "
                    + "solicitado en el/los siguiente(s) atributo(s): %s",
            objectErrorList.stream()
                    .map(fieldError -> String.format("%s", fieldError.getDefaultMessage()))
                    .collect(Collectors.joining(", ")));

    if (isNullFieldErrorPresent) {
      return buildErrorResponse.buildErrorResponse(DetalleErrorEnum.FUNCIONAL.getCode(), codeValidate, messageValidate);
    } else {
      return buildErrorResponse.buildErrorResponse(DetalleErrorEnum.FUNCIONAL.getCode(), codeFormat, messageFormat.toString());
    }
  }

  @ExceptionHandler(value = {SQLException.class, DataAccessException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleSqlException(Exception exception) {

    log.error(this.getClass().getSimpleName(), exception);

    String code = buildErrorResponse.returnCodigoError(
            DetalleErrorEnum.PREFIJO_ERROR_SISTEMA.getCode(),
            DetalleErrorEnum.PREFIJO_ERROR_CANAL_NEGOCIO.getCode(),
            DetalleErrorEnum.PREFIJO_ERROR_COMPONENTE_DATABASE.getCode(),
            DetalleErrorEnum.ERROR_DATA_BASE.getCode());

    String mensaje = "Ha ocurrido un error de base de datos: " + exception.getMessage();

    return buildErrorResponse.buildErrorResponse(DetalleErrorEnum.SISTEMA.getCode(), code, mensaje);
  }

  @ExceptionHandler(value = {Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleAllException(RuntimeException exception) {

    log.error(this.getClass().getSimpleName(), exception);

    String code = buildErrorResponse.returnCodigoError(
            DetalleErrorEnum.PREFIJO_ERROR_SISTEMA.getCode(),
            DetalleErrorEnum.PREFIJO_ERROR_CANAL_NEGOCIO.getCode(),
            DetalleErrorEnum.PREFIJO_ERROR_COMPONENTE_MICROSERVICIO.getCode(),
            DetalleErrorEnum.ERROR_GENERICO.getCode());

    String mensaje = "Ha ocurrido un error genérico: " + exception.getMessage();

    return buildErrorResponse.buildErrorResponse(DetalleErrorEnum.SISTEMA.getCode(), code, mensaje);
  }

  @ExceptionHandler(value = {NoResourceFoundException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleNoResourceFoundException(RuntimeException exception) {

    log.error(this.getClass().getSimpleName(), exception);

    String code = buildErrorResponse.returnCodigoError(
            DetalleErrorEnum.PREFIJO_ERROR_SISTEMA.getCode(),
            DetalleErrorEnum.PREFIJO_ERROR_CANAL_GENERICO.getCode(),
            DetalleErrorEnum.PREFIJO_ERROR_COMPONENTE_MICROSERVICIO.getCode(),
            DetalleErrorEnum.RUTA_NO_ENCONTRADA.getCode());

    String mensaje = "Ruta no encontrada o no valida";

    return buildErrorResponse.buildErrorResponse(DetalleErrorEnum.SISTEMA.getCode(), code, mensaje);
  }
  @ExceptionHandler(value = {ProgramNameAlreadyTakenException.class, EstudiantesDuplicatedException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleDuplicationException(Exception exception) {

    log.error(this.getClass().getSimpleName(), exception);

    String code = buildErrorResponse.returnCodigoError(
      DetalleErrorEnum.PREFIJO_ERROR_SISTEMA.getCode(),
      DetalleErrorEnum.PREFIJO_ERROR_CANAL_GENERICO.getCode(),
      DetalleErrorEnum.PREFIJO_ERROR_COMPONENTE_MICROSERVICIO.getCode(),
      DetalleErrorEnum.ERROR_DUPLICACION_REGISTRO.getCode());

    return buildErrorResponse.buildErrorResponse(DetalleErrorEnum.SISTEMA.getCode(), code, exception.getMessage());
  }
}
