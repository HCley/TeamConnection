package org.pucrs.ages

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(["ROLE_ADMIN"])
class QuestionsController {

    QuestionsService questionsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond questionsService.list(params), model:[questionsCount: questionsService.count()]
    }

    def show(Long id) {
        respond questionsService.get(id)
    }

    def create() {
        respond new Questions(params)
    }

    def save(Questions questions) {
        if (questions == null) {
            notFound()
            return
        }

        try {
            questionsService.save(questions)
        } catch (ValidationException e) {
            respond questions.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'questions.label', default: 'Questions'), questions.id])
                redirect questions
            }
            '*' { respond questions, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond questionsService.get(id)
    }

    def update(Questions questions) {
        if (questions == null) {
            notFound()
            return
        }

        try {
            questionsService.save(questions)
        } catch (ValidationException e) {
            respond questions.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'questions.label', default: 'Questions'), questions.id])
                redirect questions
            }
            '*'{ respond questions, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        questionsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'questions.label', default: 'Questions'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'questions.label', default: 'Questions'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
