package it.maelstrom.tabula.config.validation;

import it.maelstrom.tabula.common.annotation.NullOrNotBlank;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {
    public NullOrNotBlankValidator() {
    }

    public void initialize(NullOrNotBlank annotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext cxt) {
        return value == null || !value.isEmpty();
    }
}
