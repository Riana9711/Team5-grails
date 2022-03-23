package team5.grails

import grails.transaction.Transactional

import java.time.LocalDate

class BootStrap {

    def init = { servletContext ->
        anotherMagicalData()
    }
    def destroy = {
    }

    /**
     * Generation d'utilisateur et de produit
     */
    @Transactional
    def addUtilisateurAndProduit() {
        ["Harrylepap", "Sitraka", "Riana"].each { String username ->
            def utilisateurInstance = new Utilisateur(username: username, email: "$username@ituniversity-mg.com", createdAt: new Date())
            (1..5).each { Integer index ->
                def produitInstance = new Produit(
                        libelle: "Titre $username - $index",
                        description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. $utilisateurInstance@ituniversity-mg.com",
                        prix: 2 * index,
                        active: Boolean.TRUE
                )
                if (!produitInstance.save())
                    System.out.println("Le produit ne peut pas être sauvegarder")
            }
            if (!utilisateurInstance.save())
                System.out.println("L'utilisateur ne peut pas être sauvegarder")
        }
    }

    @Transactional
    def testAddProduct() {

        for (int i = 1; i<= 10; i++){
            def utilisateurInstance = new Utilisateur(username: "harrylepap", email: "rbako@ituniversity-mg.com", createdAt: new Date())
            utilisateurInstance.save()
            def produitInstance = new Produit(
                    libelle: "Test titre",
                    description: "Description Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                    prix: 12,
                    dateCreated: new Date(),
                    lastUpdated: new Date(),
                    auteur: utilisateurInstance,
                    active: Boolean.TRUE,
                    images: [new Image(filename: "produits-.jpg")]
            )
            if (!produitInstance.save(flush: true)) {
                produitInstance.errors.allErrors.each {
                    println it
                }
            }
        }

    }

    @Transactional
    def anotherMagicalData(){
        ["t-shirt", "pantalon", "chemise", "costard"].each {String libelle ->
            new Categorie(libelle: libelle).save()
        }
        ["harry", "sandie"].eachWithIndex {String username, i ->
            def utilisateurInstance = new Utilisateur(username: username, email: "$username@ituniversity-mg.com", createdAt: new Date())
            (1..5).each {
                Integer index ->
                    def produitInstance = new Produit(
                            libelle: "Test titre",
                            description: "Description Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                            prix: 12,
                            dateCreated: new Date(),
                            lastUpdated: new Date(),
                            auteur: utilisateurInstance,
                            active: Boolean.TRUE,
                            categorie: Categorie.get(i+1)
                    )
                    (1..3).each {
                        produitInstance.addToImages( new Image(filename: "fichier_$username-$index-$it-a.jpg"))
                        produitInstance.errors.getAllErrors().each {
                            println(it)
                        }
                    }
                    utilisateurInstance.addToProduits(produitInstance)
                    def commandeInstance = new Commande(utilisateur: utilisateurInstance,produit: produitInstance,nombre: (i+1),statut: Commande.STATUS_EN_ATTENTE,prixTotal: ((i+1)*12))
                    commandeInstance.save()

            }
            utilisateurInstance.save()
        }

        insertAnnonce()
    }

    @Transactional
    def insertAnnonce(){
        (1..3).each {
            def annonceInstance = new Annonce(
                    libelle: "Test $it",
                    description: "Hola",
                    active: Boolean.TRUE,
                    dateCreated: new Date(),
                    image: new Image(filename: "$it-0.jpeg")
            )
            annonceInstance.save()
        }
    }
}
