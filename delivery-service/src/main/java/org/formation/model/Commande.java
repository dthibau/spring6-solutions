package org.formation.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record Commande(
        @Pattern(regexp = "\\d{4}-\\d{5}") String noCommande,
        @Past LocalDate dateCommande,
        @Min(1) Long customerId) {}

