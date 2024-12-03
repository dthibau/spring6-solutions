package org.formation.repository;

import java.util.List;

import org.formation.model.Livraison;
import org.formation.model.Livreur;
import org.junit.Test;
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
    	l.setNoCommande("1234");
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
        livreur.setId(1l);
        assert repository.findByLivreur(livreur).isEmpty();
    }
    
    
}
