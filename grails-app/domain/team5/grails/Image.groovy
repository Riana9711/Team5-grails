package team5.grails

class Image {

    String filename
    Produit produit
    static constraints = {
        filename nullable: true, blank: true
        produit nullable: Boolean.TRUE
    }
}
