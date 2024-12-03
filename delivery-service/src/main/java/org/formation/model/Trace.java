package org.formation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.time.Instant;


@Entity
@Immutable
@Data
public class Trace {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	Status oldStatus,newStatus;
	
	private Instant date;



}
