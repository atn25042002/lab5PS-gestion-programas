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

@Documented
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@NotEmpty(message = Mensajes.NOT_EMPTY_USER_CONSUMER_ID)
@NotNull(message = Mensajes.NOT_NULL_USER_CONSUMER_ID)
@NotBlank(message = Mensajes.NOT_BLANK_USER_CONSUMER_ID)
@Pattern(
        regexp = Mensajes.REGEX_STRANGE_CHARS,
        message = Mensajes.USER_CONSUMER_ID_PATTERN_MESSAGE)
@Constraint(validatedBy = {})
public @interface UserConsumerIdConstraint {
  String message() default Mensajes.NOT_NULL_USER_CONSUMER_ID;
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
