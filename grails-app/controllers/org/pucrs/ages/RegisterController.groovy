package org.pucrs.ages

import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

@Transactional
@Secured('permitAll')
class RegisterController {

    static allowedMethods = [register: "POST"]

    def index() { }
    def register() {
        if(params.password != params.repassword) {
            //"Password and Re-Password not match"
            flash.message = message(code:"default.security.register.password.mismatch") as Object
            redirect action: "index"
        } else {
            try {
                def user = User.findByUsername(params.username)?: new User(username: params.username, password: params.password, name: params.name, registrationId: params.registration).save()
                def role = Role.findByAuthority("PENDING_APPROVAL")
                def project = Project.get(params.project.id)
                if(user && role) {
                    UserRole.create user, role
                    UserRole.withSession {
                        it.flush()
                        it.clear()
                    }

                    UserProject.create user, project

                    flash.info = message(code:"default.security.register.completed") as Object
                    redirect controller: "login", action: "auth"
                } else {
                    flash.message = "Register failed"
                    render view: "index"
                }
            } catch (ValidationException ignored) {
                println ignored.getMessage()
                flash.error = "Register Failed"
                redirect action: "index"
            }
        }
    }
}
