package is.canididate.task.validators;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = FullNameValidator.class)
@Documented
public @interface ValidFullName {
    String message() default "Невалидно име. Полето може да съдржа само букви, интервали и тирета.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}