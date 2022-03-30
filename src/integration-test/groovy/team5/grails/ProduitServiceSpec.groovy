package team5.grails

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProduitServiceSpec extends Specification {

    ProduitService produitService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Produit(...).save(flush: true, failOnError: true)
        //new Produit(...).save(flush: true, failOnError: true)
        //Produit produit = new Produit(...).save(flush: true, failOnError: true)
        //new Produit(...).save(flush: true, failOnError: true)
        //new Produit(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //produit.id
    }

    void "test get"() {
        setupData()

        expect:
        produitService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Produit> produitList = produitService.list(max: 2, offset: 2)

        then:
        produitList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        produitService.count() == 5
    }

    void "test delete"() {
        Long produitId = setupData()

        expect:
        produitService.count() == 5

        when:
        produitService.delete(produitId)
        sessionFactory.currentSession.flush()

        then:
        produitService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Produit produit = new Produit()
        produitService.save(produit)

        then:
        produit.id != null
    }
}
