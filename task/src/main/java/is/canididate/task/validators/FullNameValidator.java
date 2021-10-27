package is.canididate.task.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FullNameValidator implements ConstraintValidator<ValidFullName, String> {
    //The full name should contains cyrillic and latin letters, spaces and dashes.
    // It has not be empty and may contains maximum 90 symbols.
    private static final String NAME_PATTERN = "[A-Za-zА-Яа-я -]{1,90}";

    @Override
    public void initialize(ValidFullName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context){
        return ValidUtil.validateString(name, NAME_PATTERN);
    }
}
