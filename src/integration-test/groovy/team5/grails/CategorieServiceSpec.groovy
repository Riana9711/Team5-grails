package team5.grails

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CategorieServiceSpec extends Specification {

    CategorieService categorieService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Categorie(...).save(flush: true, failOnError: true)
        //new Categorie(...).save(flush: true, failOnError: true)
        //Categorie categorie = new Categorie(...).save(flush: true, failOnError: true)
        //new Categorie(...).save(flush: true, failOnError: true)
        //new Categorie(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //categorie.id
    }

    void "test get"() {
        setupData()

        expect:
        categorieService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Categorie> categorieList = categorieService.list(max: 2, offset: 2)

        then:
        categorieList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        categorieService.count() == 5
    }

    void "test delete"() {
        Long categorieId = setupData()

        expect:
        categorieService.count() == 5

        when:
        categorieService.delete(categorieId)
        sessionFactory.currentSession.flush()

        then:
        categorieService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Categorie categorie = new Categorie()
        categorieService.save(categorie)

        then:
        categorie.id != null
    }
}
