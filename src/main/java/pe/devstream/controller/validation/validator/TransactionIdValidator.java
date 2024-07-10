package pe.devstream.controller.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import pe.devstream.controller.validation.constraint.TransactionIdConstraint;

@Slf4j
public class TransactionIdValidator implements ConstraintValidator<TransactionIdConstraint, String> {

  @Override
  public boolean isValid(final String value, ConstraintValidatorContext context) {
    try {
      UUID uuid = UUID.fromString(value);
      log.info("TransactionId: {}", uuid);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

}
