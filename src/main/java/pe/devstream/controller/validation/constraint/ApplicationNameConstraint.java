package pe.devstream.controller.validation.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@NotEmpty(message = Mensajes.NOT_EMPTY_APPLICATION_NAME)
@NotNull(message = Mensajes.NOT_NULL_APPLICATION_NAME)
@NotBlank(message = Mensajes.NOT_BLANK_APPLICATION_NAME)
@Pattern(
        regexp = Mensajes.REGEX_STRANGE_CHARS,
        message = Mensajes.APPLICATION_NAME_PATTERN_MESSAGE)
@Constraint(validatedBy = {})
public @interface ApplicationNameConstraint {
  String message() default Mensajes.NOT_NULL;
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
