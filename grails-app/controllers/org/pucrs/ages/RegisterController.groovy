package org.pucrs.ages

import grails.plugin.springsecurity.annotation.Secured
import grails.gorm.transactions.Transactional

@Transactional
@Secured('permitAll')
class RegisterController {

    static allowedMethods = [register: "POST"]
    ProjectInterfaceService projectInterfaceService
    UserService userService

    def index() { }
    def register() {
        if(params.password != params.repassword) {
            //"Password and Re-Password not match"
            flash.message = message(code:"default.security.register.password.mismatch") as Object
            redirect action: "index"
        } else {
            try {
                if (userService.findByUsername(params.username as String))
                    throw new Exception("User already exists.")

                def user = new User(
                        username: params.username,
                        password: params.password,
                        name: params.name,
                        registrationId: params.registration,
                        ages: AGES."${params.ages.id}",
                        configuration:
                                new UserConfiguration(
                                        mode: ThemeMode."${grailsApplication.config.getProperty('application.default.theme.color')}"
                                )
                ).save(failOnError: true)

                def role = Role.findByAuthority("PENDING_APPROVAL")
                def project = projectInterfaceService.get(params.project.id as Serializable)
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
            } catch (Exception validationException) {
                flash.message = """Register Failed ${validationException.getLocalizedMessage()}"""
                redirect action: "index"
            }
        }
    }
}
