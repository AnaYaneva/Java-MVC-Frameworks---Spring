package org.softuni.residentevil.customValidations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = BeforeTodayValidator.class)
public @interface BeforeToday {
    String message() default "The date set should be before today.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}