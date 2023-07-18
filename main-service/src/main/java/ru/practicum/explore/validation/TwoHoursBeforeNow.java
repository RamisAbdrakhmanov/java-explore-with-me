package ru.practicum.explore.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;

import static java.lang.annotation.ElementType.TYPE;

@Target({ElementType.FIELD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TwoHoursBeforeNow.CustomDateValidator.class)
public @interface TwoHoursBeforeNow {

    String message() default "Field: eventDate. Error: должно содержать дату, которая еще не наступила";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    class CustomDateValidator implements ConstraintValidator<TwoHoursBeforeNow, LocalDateTime> {

        public boolean isValid(LocalDateTime dateTime, ConstraintValidatorContext constraintValidatorContext) {

            return dateTime.isAfter(LocalDateTime.now().plusHours(2));
        }
    }
}
