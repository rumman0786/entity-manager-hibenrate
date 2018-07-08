package validator;

import validator.annotation.ValidDivision;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author rumman
 * @since 7/8/18
 */
public class DivisionValidator implements ConstraintValidator<ValidDivision, String> {

    private static final List<String> VALID_DIVISIONS = Arrays.asList(
            "BARISHAL",
            "CHATTOGRAM",
            "DHAKA",
            "KHULNA",
            "MYMENSINGH",
            "RAJSHAHI",
            "RANGPUR",
            "SYLHET"
    );

    @Override
    public void initialize(ValidDivision division) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        value = Objects.isNull(value) ? "" : value.toUpperCase();

        return VALID_DIVISIONS.contains(value);
    }
}
