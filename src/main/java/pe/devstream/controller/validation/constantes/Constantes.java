package pe.devstream.controller.validation.constantes;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constantes {
  // Transaction Id
  public static final String TRANSACTION_ID = "Transaccion-Id";
  public static final String TRANSACTION_ID_EXAMPLE = "ba209999-0c6c-11d2-97cf-00c04f8eea45";
  public static final String TRANSACTION_ID_DESCRIPTION = "ID de correlación de la petición. (uuid)";

  // Application Name
  public static final String APPLICATION_NAME = "Nombre-Aplicacion";
  public static final String APPLICATION_NAME_EXAMPLE = "ms-ne-gestion-notificaciones";
  public static final String APPLICATION_NAME_DESCRIPTION = "Se refiere al nombre único del microservicio o "
          + "componente consumidora del API.";

  // User Consumer Id
  public static final String USER_CONSUMER_ID = "Usuario-Consumidor-Id";
  public static final String USER_CONSUMER_ID_EXAMPLE = "ANONIMO";
  public static final String USER_CONSUMER_ID_DESCRIPTION = "Se refiere al usuario que realiza la acción. "
          +  "Si no se tiene el usuario debe tener el valor de ANONIMO.";

  // Request


  // Response
  public static final String CODIGO_ERROR_EXAMPLE = "SNE-MS-003";
  public static final String CODIGO_ERROR_DESCRIPTION = "Codigo de error.";

  public static final String TIPO_ERROR_EXAMPLE = "SISTEMA";
  public static final String TIPO_ERROR_DESCRIPTION = "Tipo de error.";

  public static final String MENSAJE_ERROR_EXAMPLE = "Ha ocurrido un error genérico";
  public static final String MENSAJE_ERROR_DESCRIPTION = "Mensaje de respuesta de error.";

}
