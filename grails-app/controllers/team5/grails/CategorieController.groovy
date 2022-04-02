package team5.grails

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN'])
class CategorieController {

    CategorieService categorieService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond categorieService.list(params), model:[categorieCount: categorieService.count()]
    }

    def show(Long id) {
        respond categorieService.get(id)
    }

    def create() {
        respond new Categorie(params)
    }

    def save(Categorie categorie) {
        if (categorie == null) {
            notFound()
            return
        }

        try {
            categorieService.save(categorie)
        } catch (ValidationException e) {
            respond categorie.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'categorie.label', default: 'Categorie'), categorie.id])
                redirect categorie
            }
            '*' { respond categorie, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond categorieService.get(id)
    }

    def update(Categorie categorie) {
        if (categorie == null) {
            notFound()
            return
        }

        try {
            categorieService.save(categorie)
        } catch (ValidationException e) {
            respond categorie.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'categorie.label', default: 'Categorie'), categorie.id])
                redirect categorie
            }
            '*'{ respond categorie, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        categorieService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'categorie.label', default: 'Categorie'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'categorie.label', default: 'Categorie'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
