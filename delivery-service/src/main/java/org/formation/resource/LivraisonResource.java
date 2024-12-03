package org.formation.resource;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.formation.model.Livraison;
import org.formation.model.Status;
import org.formation.model.Trace;
import org.formation.repository.LivraisonRepository;
import org.formation.service.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/livraison")
public class LivraisonResource {

	protected Logger logger = Logger.getLogger(LivraisonResource.class.getName());

	@Autowired
	LivraisonService livraisonService;

	@GetMapping
	public List<Livraison> findAll() {
		return livraisonService.findAll();
	}

	@GetMapping("/{livraisonId}")
	public Livraison byId(@PathVariable("livraisonId") long livraisonId) {

		logger.info("Livraison-resource byId() invoked: " + livraisonId);

		return livraisonService.findById(livraisonId);
	}
	
	@GetMapping("/livreur/{livreurId}")
	public List<Livraison> byLivreur(@PathVariable("livreurId") long livreurId) {

		logger.info("Livraison-resource byLivreur() invoked:" + livreurId);


		return livraisonService.findByLivreurId(livreurId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Livraison createLivraison(String noCommande) {

		return livraisonService.create(noCommande);

	}
	
	@PatchMapping("/{livraisonId}")
	public Livraison changeStatus(@PathVariable("livraisonId") long livraisonId, @RequestBody Status newStatus) {

		return livraisonService.changeStatus(livraisonId,newStatus);

	}
}
