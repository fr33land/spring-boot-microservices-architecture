package lt.freeland.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import lt.freeland.common.validators.MatchValueValidator;

/**
 *
 * @author freeland
 */
@Constraint(validatedBy = MatchValueValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchingValue {

    String message() default "Fields values don't match!";

    String field();

    String matchingField();
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {

        MatchingValue[] value();
    }
}
