package mooc_project

class Illustration {

    String filename

    static belongsTo = [ad: SaleAd]

    static constraints = {
        filename blank: false, nullable: false
    }
}
