package org.formation.model;

import jakarta.persistence.*;
import lombok.Data;
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
	@ValidListInteger
	private List<Integer> integers;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public List<Integer> getIntegers() {
		return integers;
	}

	public void setIntegers(List<Integer> integers) {
		this.integers = integers;
	}
}
