package team5.grails

import grails.gorm.services.Service

@Service(Utilisateur)
interface UtilisateurService {

    Utilisateur get(Serializable id)

    List<Utilisateur> list(Map args)

    Long count()

    void delete(Serializable id)

    Utilisateur save(Utilisateur utilisateur)

}