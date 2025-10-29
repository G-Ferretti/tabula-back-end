package it.maelstrom.tabula.common.annotation;

import it.maelstrom.tabula.config.validation.NullOrNotBlankValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NullOrNotBlankValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NullOrNotBlank {
    String message() default "The field cannot be blank if present";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
