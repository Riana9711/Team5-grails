package team5.grails

import grails.gorm.services.Service

@Service(Commande)
interface CommandeService {

    Commande get(Serializable id)

    List<Commande> list(Map args)

    Long count()

    void delete(Serializable id)

    Commande save(Commande commande)

}