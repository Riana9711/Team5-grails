package team5.grails

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_CLIENT', 'ROLE_ADMIN'])
class CommandeController {

    CommandeService commandeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond commandeService.list(params), model:[commandeCount: commandeService.count()]
    }

    def show(Long id) {
        respond commandeService.get(id)
    }

    def create() {
        respond new Commande(params)
    }

    def save(Commande commande) {
        if (commande == null) {
            notFound()
            return
        }

        try {
            commandeService.save(commande)
        } catch (ValidationException e) {
            respond commande.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'commande.label', default: 'Commande'), commande.id])
                redirect commande
            }
            '*' { respond commande, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond commandeService.get(id)
    }

    def update(Commande commande) {
        if (commande == null) {
            notFound()
            return
        }

        try {
            commandeService.save(commande)
        } catch (ValidationException e) {
            respond commande.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'commande.label', default: 'Commande'), commande.id])
                redirect commande
            }
            '*'{ respond commande, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        commandeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'commande.label', default: 'Commande'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'commande.label', default: 'Commande'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
