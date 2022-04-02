package team5.grails

import grails.gorm.services.Service

@Service(Categorie)
interface CategorieService {

    Categorie get(Serializable id)

    List<Categorie> list(Map args)

    Long count()

    void delete(Serializable id)

    Categorie save(Categorie categorie)

}