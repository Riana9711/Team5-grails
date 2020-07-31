package mooc_project

import java.time.LocalDate

class SaleAd {

    String  title
    String  description
    Float   price
    Boolean status = Boolean.FALSE
    Date    dateCreated
    Date    lastUpdated
    LocalDate testDate

    static belongsTo = [author: User]

    static hasMany = [illustrations: Illustration]

    static constraints = {
        title blank: false, nullable: false, size: 5..100
        description blank: false, nullable: false
        price min: 0F, scale: 2
        status nullable: false
    }
}
