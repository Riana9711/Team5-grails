package team5.grails

class Categorie {
    String libelle

    static constraints = {
        libelle nullable: Boolean.FALSE, blank: Boolean.FALSE, size: 5..255
    }
}
