package org.pucrs.ages

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class DashboardController {

    def dashboard() { }
}
