package org.softuni.residentevil.customValidations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class BeforeTodayValidator implements ConstraintValidator<BeforeToday, LocalDate> {

    @Override
    public boolean isValid(LocalDate s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }

        LocalDate today = LocalDate.now();
        return s.isBefore(today);
    }
}
