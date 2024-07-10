package pe.devstream.service.exception;

public class ProgramNameAlreadyTakenException extends RuntimeException {

  public ProgramNameAlreadyTakenException(String message) {
    super(message);
  }
}