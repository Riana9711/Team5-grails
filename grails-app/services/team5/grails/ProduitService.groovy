package team5.grails

import grails.gorm.services.Service

@Service(Produit)
interface ProduitService {

    Produit get(Serializable id)

    List<Produit> list(Map args)

    Long count()

    void delete(Serializable id)

    Produit save(Produit produit)

}