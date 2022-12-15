package com.springbootgrades.springbootgradesapp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)// retention policy: Want annotation to be retained during the runtime
@Constraint(validatedBy = ScoreValidator.class)
public @interface Score {

    String message() default "Invalid Data";
    // group & payload parameters (must always define for custom constraint annotators)
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
