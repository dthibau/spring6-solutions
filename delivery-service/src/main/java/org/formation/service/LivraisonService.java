package org.formation.service;

import org.formation.model.Livraison;
import org.formation.model.Livreur;
import org.formation.model.Status;
import org.formation.model.Trace;
import org.formation.repository.LivraisonRepository;
import org.formation.resource.LivraisonNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class LivraisonService {

    private LivraisonRepository livraisonRepository;

    public LivraisonService(LivraisonRepository livraisonRepository) {
        this.livraisonRepository = livraisonRepository;
    }
    public Livraison create(String noCommande) {
        Livraison livraison = new Livraison();
        livraison.setNoCommande(noCommande);
        Trace trace = new Trace();
        trace.setNewStatus(Status.CREE);
        trace.setDate(Instant.now());
        livraison.addTrace(trace);
        livraison.setCreationDate(trace.getDate());
        livraison.setStatus(Status.CREE);
        return livraisonRepository.save(livraison);
    }
    public Livraison changeStatus(Long livraisonId, Status newStatus) {
        Livraison livraison = livraisonRepository.findById(livraisonId).orElseThrow(() -> new LivraisonNotFoundException("" + livraisonId));

        Status oldStatus = livraison.getStatus();
        Trace trace = new Trace();
        trace.setOldStatus(oldStatus);
        trace.setNewStatus(newStatus);
        trace.setDate(Instant.now());
        livraison.addTrace(trace);
        livraison.setStatus(newStatus);
        return livraisonRepository.save(livraison);

    }
    public List<Livraison> findAll() {
        return livraisonRepository.findAll();
    }
    public Livraison findById(Long id) {
        return livraisonRepository.findById(id).orElseThrow(() -> new LivraisonNotFoundException("" + id));
    }

    public List<Livraison> findByLivreurId(Long livreurId) {
        Livreur livreur = new Livreur();
        livreur.setId(livreurId);
        return livraisonRepository.findByLivreur(livreur);
    }
}
