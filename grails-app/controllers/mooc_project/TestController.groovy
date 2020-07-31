package mooc_project

class TestController {

    def index() {

        def userListDynamicFinders = User.findByUsername("Bob")
        println userListDynamicFinders

        def userInstanceWhereQueries = User.where{ username == "Bob" }.findAll()
        println userInstanceWhereQueries

        def userInstanceCriteria = User.createCriteria().list { eq("username", "Bob") }
        println userInstanceCriteria

        def userInstanceHQL = User.executeQuery("select u from User u where u.username = :username", [username: "Bob"])
        println userInstanceHQL

        [userInstance: userListDynamicFinders]
    }

    def test ()
    {
        def a = 1

        def closure = { a = 2 }
        closure()

        println a

        render "ok"
    }
}
