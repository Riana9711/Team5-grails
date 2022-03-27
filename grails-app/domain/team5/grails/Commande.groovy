package team5.grails

class Commande {
    Utilisateur utilisateur
    Produit produit
    Integer nombre
    Integer statut
    Double prixTotal


    static final Integer STATUS_EN_ATTENTE = 0
    static final Integer STATUS_VALIDE = 1

    static constraints = {
        utilisateur nullable: Boolean.FALSE
        produit nullable: Boolean.FALSE
        nombre nullable: Boolean.FALSE
        statut nullable: Boolean.FALSE
        prixTotal min: 0d
    }
}
