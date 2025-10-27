package org.usman.api.college.common.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.usman.api.college.common.validator.EnumValueValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Implementation Note : This class is to implement the ...
 *
 * @author UsmanFarkalit
 * @date 28-03-2024
 * @since 1.0
 */
@Documented
@Constraint(validatedBy = {EnumValueValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValidator {

    String message();

    boolean isBlankAllowed() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum> enumClass();
}
