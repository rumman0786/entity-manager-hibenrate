package validator.annotation;

import validator.DivisionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DivisionValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDivision {

    String message() default "Invalid, No such Division exists in Bangladesh";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
