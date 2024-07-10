package pe.devstream.controller.validation.constraint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import pe.devstream.controller.validation.constantes.Mensajes;
import pe.devstream.controller.validation.validator.TransactionIdValidator;

@Documented
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@NotEmpty(message = Mensajes.NOT_EMPTY_TRANSACTION_ID)
@NotNull(message = Mensajes.NOT_NULL_TRANSACTION_ID)
@Pattern(regexp = Mensajes.REGEX_TRANSACTION_ID, message = Mensajes.PATTERN_TRANSACTION_ID)
@NotBlank(message = Mensajes.NOT_BLANK_TRANSACTION_ID)
@Constraint(validatedBy = TransactionIdValidator.class)
public @interface TransactionIdConstraint {
  String message() default Mensajes.NOT_NULL;
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
