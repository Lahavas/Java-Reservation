package com.assignment.reservation.util.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidPeriodOrderValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPeriodOrder {
    String message() default "Invalid Period Order";

    String start();
    String end();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
