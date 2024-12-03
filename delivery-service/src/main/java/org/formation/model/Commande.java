package org.formation.model;

import lombok.Data;

@Data
public class Commande {

    private String noCommande;
    private String dateCommande;
    private Long customerId;
}
