package pe.devstream.controller.validation.constantes;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mensajes {
  public static final String REGEX_STRANGE_CHARS = "[^<>%=']*$";
  public static final String REGEX_TRANSACTION_ID = "^[a-zA-Z0-9-]{36}$";
  public static final String NOT_BLANK = "No pueder ser en blanco";
  public static final String NOT_EMPTY = "No pueder ser vacío";
  public static final String NOT_NULL = "No puede ser nulo";
  public static final String INVALID_CHARACTER = "No debe contener caracteres inválidos";
  public static final String FORMAT_NOT_REQUIRED = " no cumple con el formato requerido: ";
  public static final String FORMAT_FOR_UUID = "El formato debe ser de tipo UUID. Por ejemplo: "
          + "ba209999-0c6c-11d2-97cf-00c04f8eea45 (Formato entregado por chapter lead backend)";
  public static final String NOT_EMPTY_TRANSACTION_ID = Constantes.TRANSACTION_ID + FORMAT_NOT_REQUIRED + NOT_EMPTY;
  public static final String NOT_NULL_TRANSACTION_ID = Constantes.TRANSACTION_ID + FORMAT_NOT_REQUIRED + NOT_NULL;
  public static final String NOT_BLANK_TRANSACTION_ID = Constantes.TRANSACTION_ID + FORMAT_NOT_REQUIRED + NOT_BLANK;
  public static final String PATTERN_TRANSACTION_ID = Constantes.TRANSACTION_ID + FORMAT_NOT_REQUIRED + FORMAT_FOR_UUID;
  public static final String NOT_EMPTY_APPLICATION_NAME = Constantes.APPLICATION_NAME + FORMAT_NOT_REQUIRED + NOT_EMPTY;
  public static final String NOT_NULL_APPLICATION_NAME = Constantes.APPLICATION_NAME + FORMAT_NOT_REQUIRED + NOT_NULL;
  public static final String NOT_BLANK_APPLICATION_NAME = Constantes.APPLICATION_NAME + FORMAT_NOT_REQUIRED + NOT_BLANK;
  public static final String APPLICATION_NAME_PATTERN_MESSAGE = Constantes.APPLICATION_NAME + FORMAT_NOT_REQUIRED + INVALID_CHARACTER;
  public static final String NOT_EMPTY_USER_CONSUMER_ID = Constantes.USER_CONSUMER_ID + FORMAT_NOT_REQUIRED + NOT_EMPTY;
  public static final String NOT_NULL_USER_CONSUMER_ID = Constantes.USER_CONSUMER_ID + FORMAT_NOT_REQUIRED + NOT_NULL;
  public static final String NOT_BLANK_USER_CONSUMER_ID = Constantes.USER_CONSUMER_ID + FORMAT_NOT_REQUIRED + NOT_BLANK;
  public static final String USER_CONSUMER_ID_PATTERN_MESSAGE = Constantes.USER_CONSUMER_ID + FORMAT_NOT_REQUIRED + INVALID_CHARACTER;
}
