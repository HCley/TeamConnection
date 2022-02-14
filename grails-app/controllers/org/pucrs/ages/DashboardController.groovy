package org.pucrs.ages

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class DashboardController {

    SpringSecurityService springSecurityService
    DashboardService dashboardService
    UserService userService

    def index() {
        respond new Object(), model: [theme: userService.getTheme(springSecurityService.currentUser as User)]
    }

}
