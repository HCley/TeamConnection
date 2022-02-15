package org.pucrs.ages

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_PROFESSOR", "ROLE_AGESI", "ROLE_AGESII", "ROLE_AGESIII", "ROLE_AGESIV"])
class DashboardController {

    SpringSecurityService springSecurityService
    DashboardService dashboardService
    UserService userService

    def index() {
        def user = springSecurityService.currentUser as User
        respond user, model: [theme: userService.getTheme(user)]
    }

}
