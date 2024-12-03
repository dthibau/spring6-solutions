package org.formation.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Livreur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@UuidGenerator(style = UuidGenerator.Style.TIME)
	private UUID id;

	@ElementCollection
	@CollectionTable(name = "livreur_reviews", joinColumns = @JoinColumn(name = "livreur_id"))
	@Min(1)  // Chaque entier doit être >= 1
	@Max(5)  // Chaque entier doit être <= 5
	private List<Integer> integers;


}
