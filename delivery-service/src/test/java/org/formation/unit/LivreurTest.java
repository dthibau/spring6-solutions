package org.formation.unit;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.formation.model.Livreur;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LivreurTest {
    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldFailWhenIntegerIsLessThanMin() {
        Livreur livreur = new Livreur();
        livreur.setId(UUID.randomUUID());
        livreur.setIntegers(List.of(0, 3, 4)); // 0 est invalide

        Set<ConstraintViolation<Livreur>> violations = validator.validate(livreur);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(violation -> violation.getMessage().contains("Les entiers doivent être entre 1 et 5")));
    }

    @Test
    void shouldFailWhenIntegerExceedsMax() {
        Livreur livreur = new Livreur();
        livreur.setId(UUID.randomUUID());
        livreur.setIntegers(List.of(3, 6)); // 6 est invalide

        Set<ConstraintViolation<Livreur>> violations = validator.validate(livreur);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(violation -> violation.getMessage().contains("Les entiers doivent être entre 1 et 5")));
    }

    @Test
    void shouldPassWhenIntegersAreWithinRange() {
        Livreur livreur = new Livreur();
        livreur.setId(UUID.randomUUID());
        livreur.setIntegers(List.of(1, 2, 3, 4, 5)); // Tout est valide

        Set<ConstraintViolation<Livreur>> violations = validator.validate(livreur);

        assertTrue(violations.isEmpty());
    }
}
