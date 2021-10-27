package is.canididate.task.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PinValidator implements ConstraintValidator<ValidPin, String> {

    private static final String PIN_PATTERN = "^$|(^[0-9]{0,10}$)";
    @Override
    public void initialize(ValidPin constraintAnnotation) {
    }

    @Override
    public boolean isValid(String pin, ConstraintValidatorContext context){
        return ValidUtil.validateString(pin, PIN_PATTERN);
    }
}
