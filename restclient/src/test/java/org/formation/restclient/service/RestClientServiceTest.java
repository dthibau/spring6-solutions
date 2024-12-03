package org.formation.restclient.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RestClientServiceTest {

    @Autowired
    RestClientService restClientService;

    @Test
    public void testFindById() {

        assertThat(restClientService.getLivraisonById(1l)).contains("1");

        assertThat(restClientService.getLivraisonById(999l)).contains("404");

    }

    @Test
    public void testCreate() {

        assertThat(restClientService.createLivraison("1111-11111")).contains("1111-11111");

    }

    @Test
    public void testPatch() {

        assertThat(restClientService.patchLivraisonById(1l,"\"LIVREUR_AFFECTE\"")).contains("LIVREUR_AFFECTE");

    }
}
