package org.formation.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

@Entity
@Data
public class Livraison {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Pattern(regexp = "\\d{4}-\\d{5}")
	private String noCommande;

	@Column(name = "commande")
	@JdbcTypeCode(SqlTypes.JSON)
	private Commande commande;
	
	@OneToOne
	private Livreur livreur;
	
	private Status status;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Trace> historique = new ArrayList<>();

	private Instant creationDate;

	public void addTrace(Trace trace) {
		historique.add(trace);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Livraison livraison = (Livraison) o;
		return id == livraison.id;
	}

	@Override
	public int hashCode() {
		return Long.hashCode(id);
	}
}
