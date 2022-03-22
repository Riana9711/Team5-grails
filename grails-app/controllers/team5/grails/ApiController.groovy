package team5.grails

import grails.converters.JSON
import grails.converters.XML

class ApiController {

    //api/produits/{id}
    def produits() {
        def produitInstance

        // recuperation de la methode HTTP de l'utilisateur
        switch (request.getMethod()) {
            case "GET":
                if (!params.id) {
                    produitInstance = Produit.list()
                } else {
                    produitInstance = Produit.get(params.id)
                }

                if (!produitInstance)
                    response.status = 404

                response.withFormat {
                    json { render produitInstance as JSON }
                    xml { render produitInstance as XML }
                }
                break

            case "POST":
                def dataUtilisateur = request.JSON
                produitInstance = new Produit(dataUtilisateur)

                produitInstance.validate()
                if (produitInstance.hasErrors()) {
                    response.status = 422
                    def erreurs = produitInstance.errors.allErrors.collect {
                        message(error: it)
                    }
                    render( erreurs as JSON)
                }

                produitInstance.save()
                return response.status = 204

                break

            case "PUT":
                /*
                if (!params.id)
                    return response.status = 405

                produitInstance = Produit.get(params.id)
                def jsonDATA = request.JSON

                produitInstance.properties.each { propriete, valeur ->
                    jsonDATA.each { key, value ->
                        if (key == propriete) {
                            produitInstance.setProperty(key, value)
                        }
                    }

                }

                def updatedProduit = new Produit(produitInstance)

                if (!updatedProduit.save(flush: true)) {
                    updatedProduit.errors.allErrors.each {
                        println it
                    }
                    return updatedProduit.errors.fieldErrors as JSON
                } else {
                    response.withFormat {
                        json { render updatedProduit as JSON }
                        xml { render updatedProduit as XML }
                    }
                }
*/
                break
            case "DELETE":
                break
            default:
                break
        }
        return response.status = 406
    }


    def searchProductByLibelle() {
        def produits
        // recuperation de la methode HTTP de l'utilisateur
        switch (request.getMethod()) {
            case "GET":
                if (!params.id) {
                    return response.status = 400
                }
                produits = Produit.findAllByLibelleLike("%$params.id%")
                if (!produits)
                    response.status = 404

                response.withFormat {
                    json { render produits as JSON }
                    xml { render produits as XML }
                }
                break

            case "POST":
                break

            case "PUT":
                break
            case "DELETE":
                break
            default:
                break
        }
        return response.status = 406
    }

    def findAllProductsByCategorie() {
        def produits
        // recuperation de la methode HTTP de l'utilisateur
        switch (request.getMethod()) {
            case "GET":
                if (!params.id) {
                    return response.status = 400
                }
                produits = Produit.findAllByCategorie(Categorie.get(params.id))
                if (!produits)
                    response.status = 404

                response.withFormat {
                    json { render produits as JSON }
                    xml { render produits as XML }
                }
                break

            case "POST":
                break

            case "PUT":
                break
            case "DELETE":
                break
            default:
                break
        }
        return response.status = 406
    }
}
