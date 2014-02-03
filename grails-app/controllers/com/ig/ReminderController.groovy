package com.ig

import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional


@Secured(["ROLE_USER"])
@Transactional(readOnly = true)
class ReminderController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Reminder.list(params), model:[reminderInstanceCount: Reminder.count()]
    }

    def show(Reminder reminderInstance) {
        respond reminderInstance
    }

    def create() {
        respond new Reminder(params)
    }

    @Transactional
    def save(Reminder reminderInstance) {
        if (reminderInstance == null) {
            notFound()
            return
        }

        if (reminderInstance.hasErrors()) {
            respond reminderInstance.errors, view:'create'
            return
        }

        reminderInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'reminderInstance.label', default: 'Reminder'), reminderInstance.id])
                redirect reminderInstance
            }
            '*' { respond reminderInstance, [status: CREATED] }
        }
    }

    def edit(Reminder reminderInstance) {
        respond reminderInstance
    }

    @Transactional
    def update(Reminder reminderInstance) {
        if (reminderInstance == null) {
            notFound()
            return
        }

        if (reminderInstance.hasErrors()) {
            respond reminderInstance.errors, view:'edit'
            return
        }

        reminderInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Reminder.label', default: 'Reminder'), reminderInstance.id])
                redirect reminderInstance
            }
            '*'{ respond reminderInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Reminder reminderInstance) {

        if (reminderInstance == null) {
            notFound()
            return
        }

        reminderInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Reminder.label', default: 'Reminder'), reminderInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'reminderInstance.label', default: 'Reminder'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
