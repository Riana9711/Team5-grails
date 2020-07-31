package mooc_project

import java.time.LocalDate

class User {

    String  username
    Date    dateCreated

    static hasMany = [sales: SaleAd]

    static constraints = {
        username blank: false, nullable: false, unique: true
    }
}
