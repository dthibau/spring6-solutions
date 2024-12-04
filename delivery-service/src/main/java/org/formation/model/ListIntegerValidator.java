package org.formation.model;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ListIntegerValidator implements ConstraintValidator<ValidListInteger, List<Integer>> {

    @Override
    public boolean isValid(List<Integer> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // On considère qu'une liste nulle est valide
        }
        return value.stream().allMatch(i -> i >= 1 && i <= 5);
    }
}
