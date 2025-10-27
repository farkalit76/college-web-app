package org.usman.api.college.common.validator;


import io.micrometer.core.instrument.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.usman.api.college.common.annotation.EnumValidator;

/**
 * Implementation Note : This class is to implement the ...
 *
 * @author UsmanFarkalit
 * @date 28-03-2024
 * @since 1.0
 */
public class EnumValueValidator implements ConstraintValidator<EnumValidator, String> {

    private EnumValidator enumValidator;

    @Override
    public void initialize(EnumValidator enumValidator) {
        this.enumValidator = enumValidator;
    }

    @Override
    public boolean isValid(
            String valueForValidation, ConstraintValidatorContext constraintValidatorContext) {

        if (enumValidator.isBlankAllowed() && StringUtils.isBlank(valueForValidation)) {
            return true;
        }

        for (Enum<?> enumValue : this.enumValidator.enumClass().getEnumConstants()) {

            if (isValidEnumValue(valueForValidation, enumValue)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidEnumValue(String valueForValidation, Enum<?> enumValue) {
        return StringUtils.isNotBlank(valueForValidation)
                && valueForValidation.equalsIgnoreCase(enumValue.toString());
    }
}