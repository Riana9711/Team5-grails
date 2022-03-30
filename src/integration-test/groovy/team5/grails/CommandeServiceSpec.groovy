package team5.grails

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CommandeServiceSpec extends Specification {

    CommandeService commandeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Commande(...).save(flush: true, failOnError: true)
        //new Commande(...).save(flush: true, failOnError: true)
        //Commande commande = new Commande(...).save(flush: true, failOnError: true)
        //new Commande(...).save(flush: true, failOnError: true)
        //new Commande(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //commande.id
    }

    void "test get"() {
        setupData()

        expect:
        commandeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Commande> commandeList = commandeService.list(max: 2, offset: 2)

        then:
        commandeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        commandeService.count() == 5
    }

    void "test delete"() {
        Long commandeId = setupData()

        expect:
        commandeService.count() == 5

        when:
        commandeService.delete(commandeId)
        sessionFactory.currentSession.flush()

        then:
        commandeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Commande commande = new Commande()
        commandeService.save(commande)

        then:
        commande.id != null
    }
}
