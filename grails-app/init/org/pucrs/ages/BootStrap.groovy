package org.pucrs.ages

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        if (Environment.current.name == "development") {
            def role = new Role(authority: 'ROLE_ADMIN').save()
            def user = new User(
                    username: 'admin',
                    password: '123456',
                    name: 'Administrator',
                    registrationId: "99999999-9",
                    configuration: new UserConfiguration(mode: ThemeMode.DARK)
            ).save(failOnError: true)

            UserRole.create user, role

            new Role(authority: 'PENDING_APPROVAL').save(failOnError: true)
            new Role(authority: 'PROFESSOR').save(failOnError: true)

            new Role(authority: 'AGES_I').save(failOnError: true)
            new Role(authority: 'AGES_II').save(failOnError: true)
            new Role(authority: 'AGES_III').save(failOnError: true)
            new Role(authority: 'AGES_IV').save(failOnError: true)

            new Project(projectName: "Garbus", semester: "20/2").save()

//            new UserConfiguration(user: user, mode: UserConfiguration.Mode.DARK).save(failOnError: true)
        }
    }

    def destroy = {}
}
