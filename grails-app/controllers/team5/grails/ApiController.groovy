package team5.grails

import grails.converters.JSON
import grails.converters.XML
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

@Secured(value=["hasRole('ROLE_CLIENT')"])
class ApiController {

    SpringSecurityService springSecurityService

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

    def annonces() {
        def annonceInstance

        // recuperation de la methode HTTP de l'annonce
        switch (request.getMethod()) {
            case "GET":
                if (!params.id) {
                    annonceInstance = Annonce.list()
                } else {
                    annonceInstance = Annonce.get(params.id)
                }

                if (!annonceInstance)
                    response.status = 404

                response.withFormat {
                    json { render annonceInstance as JSON }
                    xml { render annonceInstance as XML }
                }
                break

            case "POST":
                def dataannonce = request.JSON
                annonceInstance = new Annonce(dataannonce)

                annonceInstance.validate()
                if (annonceInstance.hasErrors()) {
                    response.status = 422
                    def erreurs = annonceInstance.errors.allErrors.collect {
                        message(error: it)
                    }
                    render( erreurs as JSON)
                }

                annonceInstance.save()
                return response.status = 204

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

    def searchAdvancedProduct() {
        def produits
        println("+++++++++ debut ++++++++++")
        println(request.requestURL)
        println(request.queryString)
        // recuperation de la methode HTTP de l'utilisateur
        switch (request.getMethod()) {
            case "GET":
                // check existance params
                if (!params.offset || !params.limit) {
                    return response.status = 400
                }
                int offset, limit;
                def libelle = params.libelle ? params.libelle : ""
                def categorie = params.categorie
                int total = 0;
                // check integrite valeur params
                try{
                    offset = Integer.parseInt(params.offset);
                    limit = Integer.parseInt(params.limit);
                }catch(Exception e){
                    return response.status = 400
                }

                if (categorie){
                    total = Produit.countByCategorieAndLibelleLike(Categorie.get(categorie),"%$libelle%")
                    produits = Produit.findAllByCategorieAndLibelleLike(Categorie.get(categorie),"%$libelle%",[max: limit, offset: offset])
                }else{
                    total = Produit.countByLibelleLike("%$libelle%")
                    produits = Produit.findAllByLibelleLike("%$libelle%",[max: limit, offset: offset])
                }
                println("Nombre total = "+produits.size()+"/20")

                if (!produits)
                    response.status = 404

                def response = [
                        "produits": produits,
                        "total": total
                ]
                println("+++++++++ fin ++++++++++")

                render response as JSON
                /*response.withFormat {
                    json { render { produits: produits } as JSON }
                    xml { render { produits: produits } as XML }
                }*/
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

    def categories(){
        def categoriesInst

        switch (request.getMethod()) {
            case "GET":
                categoriesInst = Categorie.list()

                if (!categoriesInst)
                    response.status = 404

                response.withFormat {
                    json { render categoriesInst as JSON }
                    xml { render categoriesInst as XML }
                }
                break
            default:
                break
        }
        return response.status = 406
    }

    def findAllCommandByCurrentUser() {
        def commandesInst

        switch (request.getMethod()) {
            case "GET":
                long currentUserId = springSecurityService.getCurrentUserId();
                commandesInst = Commande.findAllByUtilisateur(Utilisateur.get(currentUserId));
                if (!commandesInst)
                    response.status = 404

                def commandesList= []
                commandesInst.each {Commande commande ->
                    def commandeInfo = [:]
                    commandeInfo['idCommande'] = commande.id
                    commandeInfo['nombre'] = commande.nombre
                    commandeInfo['statut'] = commande.statut
                    def statutValue = ''
                    switch (commande.statut) {
                        case Commande.STATUS_EN_PANIER:
                            statutValue = 'En panier'
                            break
                        case Commande.STATUS_EN_ATTENTE:
                            statutValue = 'En attente de validation'
                            break
                        case Commande.STATUS_VALIDE:
                            statutValue = 'Validé'
                            break
                        default:
                            break
                    }
                    commandeInfo['statutValue'] = statutValue
                    commandeInfo['prixTotal'] = commande.prixTotal
                    Produit produitInstance = Produit.get(commande.produit.id)
                    commandeInfo['nomProduit'] = produitInstance.libelle
                    commandeInfo['prixProduit'] = produitInstance.prix
                    commandesList.add(commandeInfo)
                }

                response.withFormat {
                    json { render commandesList as JSON }
                    xml { render commandesList as XML }
                }
                break
            default:
                break
        }
        return response.status = 406
    }

    def insertCommande() {
        switch (request.getMethod()) {
            case "POST":
                if (!request.JSON.idProduit || !request.JSON.nombre) {
                    return response.status = 400
                }
                long currentUserId = springSecurityService.getCurrentUserId();
                def utilisateurInstance = Utilisateur.get(currentUserId)
                def produitInstance = Produit.get(request.JSON.idProduit)
                if (!utilisateurInstance || !produitInstance)
                    return response.status = 404
                Integer nombre = request.JSON.nombre
                Double prix = produitInstance.prix
                def newCommande = new Commande(utilisateur: utilisateurInstance, produit: produitInstance, nombre: nombre, statut: Commande.STATUS_EN_PANIER    , prixTotal: nombre*prix)

                def message = ''
                def status = ''
                if (!newCommande.save()) {
                    status = 'error'
                    newCommande.errors.allErrors.each {
                        message = it
                    }
                }
                else
                    status = 'success'

                def responseData = [
                        'status': status,
                        'message': message,
                ]

                response.withFormat {
                    json { render responseData as JSON }
                    xml { render responseData as XML }
                }
                break
            default:
                break
        }
    }

    def doCommande() {
        switch (request.getMethod()) {
            case "POST":
                def message = ''
                def status = ''
                try {
                    if (!request.JSON.idCommande ) {
                        return response.status = 400
                    }
                    def commande = Commande.get(request.JSON.idCommande)

                    if(!commande) {
                        response.status = 404
                    }

                    commande.setStatut(Commande.STATUS_EN_ATTENTE)
                    commande.save(flush: true)
                    status = 'success'
                }
                catch (Exception e){
                    status = 'error'
                    println e.getMessage()
                    message = e.getMessage()
                }

                def responseData = [
                    'status': status,
                    'message': message,
                ]

                response.withFormat {
                    json { render responseData as JSON }
                    xml { render responseData as XML }
                }
                break
            default:
                break
        }
    }
    def deleteCommande() {
        switch (request.getMethod()) {
            case "DELETE":
                if (!params.id ) {
                    return response.status = 400
                }
                def commandeToDelete = Commande.get(params.id)
                if (!commandeToDelete) {
                    return response.status = 404
                }
                commandeToDelete.delete(flush: true)
                def message = ''
                def status = ''
                if (!commandeToDelete.errors.allErrors.isEmpty()) {
                    status = 'error'
                    commandeToDelete.errors.allErrors.each {
                        message = it
                    }
                }
                else
                    status = 'success'

                def responseData = [
                        'status': status,
                        'message': message,
                ]

                response.withFormat {
                    json { render responseData as JSON }
                    xml { render responseData as XML }
                }
                break
            default:
                break
        }
    }
}
