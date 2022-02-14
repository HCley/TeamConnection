package org.pucrs.ages

import grails.gorm.transactions.Transactional
import grails.core.GrailsApplication

@Transactional
class UserService {

    GrailsApplication grailsApplication

    def getTheme(User user) {
        try { return user?.getConfiguration()?.getMode() } catch (e) {
            log.error "Error on DashboardService/getTheme: ${e.message}"
        }
        UserConfiguration.Mode."${grailsApplication.config.getProperty('application.default.theme.color')}"
    }
}
