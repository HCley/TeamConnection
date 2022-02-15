package org.pucrs.ages

import grails.gorm.transactions.Transactional
import grails.core.GrailsApplication
import org.springframework.context.MessageSource

import java.util.stream.Collectors


@Transactional
class UserService {

    GrailsApplication grailsApplication
    def messageSource

    def findByUsername(String username) {
        User.findByUsername(username)
    }

    def getTheme(User user) {
        try {
            return user?.getConfiguration()?.getMode()
        } catch (e) {
            log.error "Error on DashboardService/getTheme: ${e.message}"
        }
        ThemeMode."${grailsApplication.config.getProperty('application.default.theme.color')}"
    }

    def findPendingUsers() {
        UserRole.findAllByRole (
            Role.findByAuthority("PENDING_APPROVAL")
        )
            .stream()
            .map({ it -> it.user })
            .collect(Collectors.toList())
    }

    static def findProjectByUsers(User user) {
        def project = UserProject.findByUser(user)?.project
        if(!project)
            return "-"
        project
    }

    static def hasHigherPermissions(User user) {
        user?.getAuthorities()?.findAll {role ->
            ["ROLE_ADMIN", "ROLE_PROFESSOR"].any {it.contains(role.authority) }
        }
    }
}
