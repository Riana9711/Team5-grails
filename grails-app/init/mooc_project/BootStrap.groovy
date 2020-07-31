package mooc_project

import java.time.LocalDate

class BootStrap {

    def init = { servletContext ->
        ["Greg", "Guillaume", "Bob"].each {
            def username ->
                def userInstance = new User(username: username)
                (1..5).each {
                    def index ->
                        def saleAdInstance = new SaleAd(title: "title $username $index", testDate: LocalDate.now(), description: "Nice description", price: 5 * index, status: Boolean.TRUE)
                        (1..3).each {
                            def illustration ->
                                saleAdInstance.addToIllustrations(new Illustration(filename: "$username-$index-$illustration-.png"))
                        }
                        userInstance.addToSales(saleAdInstance)
                        userInstance.save()
                }
        }
    }
    def destroy = {
    }
}
