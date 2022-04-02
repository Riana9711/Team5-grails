package team5.grails

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN'])
class UtilisateurController {

    UtilisateurService utilisateurService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond utilisateurService.list(params), model:[utilisateurCount: utilisateurService.count()]
    }

    def show(Long id) {
        respond utilisateurService.get(id)
    }

    def create() {
        respond new Utilisateur(params)
    }

    def save(Utilisateur utilisateur) {
        if (utilisateur == null) {
            notFound()
            return
        }

        try {
            utilisateurService.save(utilisateur)
        } catch (ValidationException e) {
            respond utilisateur.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), utilisateur.id])
                redirect utilisateur
            }
            '*' { respond utilisateur, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond utilisateurService.get(id)
    }

    def update(Utilisateur utilisateur) {
        if (utilisateur == null) {
            notFound()
            return
        }

        try {
            utilisateurService.save(utilisateur)
        } catch (ValidationException e) {
            respond utilisateur.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), utilisateur.id])
                redirect utilisateur
            }
            '*'{ respond utilisateur, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        utilisateurService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
