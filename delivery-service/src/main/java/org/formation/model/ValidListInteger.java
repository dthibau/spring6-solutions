package org.formation.model;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ListIntegerValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidListInteger {

    String message() default "Les entiers doivent être entre 1 et 5";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}