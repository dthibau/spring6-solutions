package org.formation.repository;

import org.formation.model.Livraison;
import org.formation.model.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LivraisonRepository extends JpaRepository<Livraison, Long>{

    List<Livraison> findByLivreur(Livreur livreur);

    @Query("SELECT MIN(t.date) FROM Livraison l JOIN l.historique t WHERE l.id = :livraisonId")
    Optional<LocalDateTime> findEarliestDateInHistorique(@Param("livraisonId") long livraisonId);

    @Query("SELECT ROUND(AVG(EXTRACT(YEAR FROM t.date))) FROM Livraison l JOIN l.historique t WHERE l.id = :livraisonId")
    Optional<Float> findAverageYearInHistorique(@Param("livraisonId") long livraisonId);

}
