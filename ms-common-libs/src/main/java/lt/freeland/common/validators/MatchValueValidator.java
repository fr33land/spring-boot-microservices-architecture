package lt.freeland.common.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lt.freeland.common.annotations.MatchingValue;
import org.springframework.beans.BeanWrapperImpl;

/**
 *
 * @author freeland
 */
public class MatchValueValidator implements ConstraintValidator<MatchingValue, Object> {
 
    private String field;
    private String fieldMatch;
 
    @Override
    public void initialize(MatchingValue constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.matchingField();
    }
 
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
 
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
         
        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}
