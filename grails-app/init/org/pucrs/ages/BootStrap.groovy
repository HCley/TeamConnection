package org.pucrs.ages

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        if (Environment.current.name == "development") {
            def role = new Role(authority: 'ROLE_ADMIN').save()
            def user = new User(username: 'admin', password: '123456', name: 'Administrator', registrationId: "99999999-9").save()
            UserRole.create user, role

            new Role(authority: 'PENDING_APPROVAL').save()
            new Role(authority: 'PROFESSOR').save()

            new Role(authority: 'AGES_I').save()
            new Role(authority: 'AGES_II').save()
            new Role(authority: 'AGES_III').save()
            new Role(authority: 'AGES_IV').save()

            new Project(projectName: "Garbus", semester: "20/2").save()
        }
    }

    def destroy = {}
}
