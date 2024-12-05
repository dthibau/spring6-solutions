package org.formation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Livraison implements Serializable {

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNoCommande() {
		return noCommande;
	}

	public void setNoCommande(String noCommande) {
		this.noCommande = noCommande;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Livreur getLivreur() {
		return livreur;
	}

	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Trace> getHistorique() {
		return historique;
	}

	public void setHistorique(List<Trace> historique) {
		this.historique = historique;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
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
