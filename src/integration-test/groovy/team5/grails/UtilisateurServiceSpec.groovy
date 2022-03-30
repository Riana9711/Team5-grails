package team5.grails

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UtilisateurServiceSpec extends Specification {

    UtilisateurService utilisateurService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Utilisateur(...).save(flush: true, failOnError: true)
        //new Utilisateur(...).save(flush: true, failOnError: true)
        //Utilisateur utilisateur = new Utilisateur(...).save(flush: true, failOnError: true)
        //new Utilisateur(...).save(flush: true, failOnError: true)
        //new Utilisateur(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //utilisateur.id
    }

    void "test get"() {
        setupData()

        expect:
        utilisateurService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Utilisateur> utilisateurList = utilisateurService.list(max: 2, offset: 2)

        then:
        utilisateurList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        utilisateurService.count() == 5
    }

    void "test delete"() {
        Long utilisateurId = setupData()

        expect:
        utilisateurService.count() == 5

        when:
        utilisateurService.delete(utilisateurId)
        sessionFactory.currentSession.flush()

        then:
        utilisateurService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Utilisateur utilisateur = new Utilisateur()
        utilisateurService.save(utilisateur)

        then:
        utilisateur.id != null
    }
}
