package team5.grails

class Commande {
    Utilisateur utilisateur
    Produit produit
    Integer nombre
    Integer statut
    Double prixTotal


    static final Integer STATUS_EN_PANIER = 0
    static final Integer STATUS_EN_ATTENTE = 1
    static final Integer STATUS_VALIDE = 2

    static constraints = {
        utilisateur nullable: Boolean.FALSE
        produit nullable: Boolean.FALSE
        nombre nullable: Boolean.FALSE
        statut nullable: Boolean.FALSE
        prixTotal min: 0d
    }
}
