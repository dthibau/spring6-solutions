package org.formation.repository;

import org.formation.model.Livraison;
import org.formation.model.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivraisonRepository extends JpaRepository<Livraison, Long>{

    List<Livraison> findByLivreur(Livreur livreur);
}
