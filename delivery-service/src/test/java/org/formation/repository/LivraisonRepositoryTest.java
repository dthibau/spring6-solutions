package org.formation.repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.formation.model.Livraison;
import org.formation.model.Livreur;
import org.junit.Test;
import java.util.Optional;

import org.formation.model.Trace;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.ApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
public class LivraisonRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest");

	@Autowired
	ApplicationContext context;
	
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LivraisonRepository repository;
    
    
    @BeforeEach
    public void setup() {
      
    	Livraison l = new Livraison();
    	l.setNoCommande("1234-12345");
        Trace trace = new Trace();
        trace.setDate(Instant.now());
        l.addTrace(trace);
    	entityManager.persist(l);
      
        
    }
    @Test
    public void testFindAll() throws Exception {
    	
    	List<Livraison> livraisons = repository.findAll();
    	assert !livraisons.isEmpty();
    }
    @Test
    public void testFindById() throws Exception {

        assert repository.findById(1l) != null;
    }
    @Test
    public void testFindByLivreur() throws Exception {
        Livreur livreur = new Livreur();
        livreur.setId(UUID.randomUUID());
        assert repository.findByLivreur(livreur).isEmpty();
    }
    

    @Test
    public void testFindEarliest() throws Exception {

        Optional<LocalDateTime> optional = repository.findEarliestDateInHistorique(1);
        assert !optional.isEmpty();
    }

    @Test
    public void testFindAverageYear() throws Exception {

       Optional<Float> optional = repository.findAverageYearInHistorique(1);
       assert !optional.isEmpty();
    }
}
