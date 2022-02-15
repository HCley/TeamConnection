package org.pucrs.ages

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

@Secured(["ROLE_ADMIN", "ROLE_PROFESSOR"])
class UserController {

//    UserConfigurationInterfaceService userConfigurationService
    SpringSecurityService springSecurityService
    UserInterfaceService userInterfaceService
    UserService userService


    def configurations() {
        def user = springSecurityService.currentUser as User
        respond user, model:[theme: userService.getTheme(user)]
    }

    @Secured(["ROLE_ADMIN", "ROLE_PROFESSOR"])
    def pending() {
        def user = springSecurityService.currentUser as User
        def list = userService.findPendingUsers()
        def count = list.size()
        respond user, model:[pendingList: list, pendingCount: count, theme: userService.getTheme(user)]
    }

    def update() {
        def user = springSecurityService.currentUser as User

        try {
            user.getConfiguration().setMode(ThemeMode."${params.theme.id}")
            if(params.password) {
                if(params.password != params.repassword) {
                    //"Password and Re-Password not match"
                    flash.message = message(code: "default.security.register.password.mismatch") as Object
                    redirect action: "configurations"
                    return
                }
                user.setPassword(params.password as String)
            }

            try {
                userInterfaceService.save(user)
            } catch (ValidationException ignored) {
                flash.message = """Registration error: ${user.errors}"""
                respond user.errors, view:'configurations'
                return
            }

            flash.info = message(code:"default.security.register.completed") as Object
            redirect controller: "dashboard", action: "index"
        } catch (ValidationException validationException) {
            flash.message = """Registration error: ${validationException.getMessage()}"""
            redirect controller: "dashboard", action: "index"
        }
    }
}
