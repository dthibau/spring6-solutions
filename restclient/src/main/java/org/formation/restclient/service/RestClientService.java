package org.formation.restclient.service;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RestClientService {

    public static
    RestClient restClient;

    RestClientService() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    public String createLivraison(String noCommande) {

        return restClient.post()
                .uri("/api/livraison?noCommande={noCommande}", noCommande)
                .retrieve()
                .body(String.class);
    }

    public String getLivraisonById(long id) {

        return restClient.get()
                .uri("/api/livraison/{id}", id)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        (req, resp ) -> {
                            System.err.println("Erreur 4xx : " + resp.getStatusCode());
                        }

                )
                .body(String.class);
    }

    public String patchLivraisonById(long id, String statut) {

        return restClient.patch()
                .uri("/api/livraison/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(statut)
                .retrieve()
                .body(String.class);
    }
}
