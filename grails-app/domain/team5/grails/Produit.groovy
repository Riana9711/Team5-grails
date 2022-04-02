package team5.grails

class Produit {

    String libelle
    String description
    Double prix
    Boolean active
    Date dateCreated
    Date lastUpdated
    Categorie categorie
    int stock


    static belongsTo = [auteur: Utilisateur]

    static hasMany = [images: Image]

    static mapping = {
        description type: 'text'
        dateCreated date: new Date()
        active TRUE: Boolean.TRUE
    }

    static constraints = {
        libelle nullable: Boolean.FALSE, blank: Boolean.FALSE, size: 5..255
        description nullable: Boolean.FALSE, blank: Boolean.FALSE
        stock min: 0
        prix min: 0d
        dateCreated nullable: Boolean.TRUE
        lastUpdated nullable: Boolean.TRUE
        categorie nullable: Boolean.FALSE
    }

    String toString() { return libelle }

}
