package com.springbootgrades.springbootgradesapp;

import java.util.Arrays;
import java.util.List;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
//import jakarta.validation.OverridesAttribute.List;

public class ScoreValidator implements ConstraintValidator<Score, String> {
    
    List<String> scores = Arrays.asList(
        "A+", "A", "A-" ,
        "B+", "B", "B-" ,
        "C+", "C", "C-" ,
        "D+", "D", "D-" ,
        "F"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // check user input value against each accepted grade value in the array
        for (String string : scores) {
            if (value.equals(string)) return true;
        }
        return false;
    }
}
