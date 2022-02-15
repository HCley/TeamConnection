package org.pucrs.ages

import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        if (Environment.current.name == "development") {
            def project = new Project(projectName: "RevivArq", semester: "20/2").save(failOnError: true)

            // ADMIN
            def role = new Role(authority: 'ROLE_ADMIN').save()
            def user = new User(
                    username: 'admin',
                    password: '123456',
                    name: 'Administrator',
                    registrationId: "99999999-9",
                    ages: AGES.IV,
                    configuration: new UserConfiguration(mode: ThemeMode.DARK)
            ).save(failOnError: true)
            UserRole.create user, role

            // PROFESSOR
            role = new Role(authority: 'ROLE_PROFESSOR').save()
            user = new User(
                    username: 'professor',
                    password: '123456',
                    name: 'Professor',
                    registrationId: "00000000-0",
                    ages: AGES.IV,
                    configuration: new UserConfiguration(mode: ThemeMode.DARK)
            ).save(failOnError: true)
            UserRole.create user, role
            UserProject.create user, project

            project = new Project(projectName: "Garbus", semester: "20/2").save(failOnError: true)
            UserProject.create user, project

            // Cleyson - AGES III
            user = new User(
                    username: 'cleyson',
                    password: '442498',
                    name: 'Cleyson Oliveira',
                    registrationId: "18203082-5",
                    ages: AGES.III,
                    configuration: new UserConfiguration(mode: ThemeMode.DARK)
            ).save(failOnError: true)
            role = new Role(authority: 'ROLE_AGESIII').save(failOnError: true)
            UserRole.create user, role
            UserProject.create user, project

            // MOCKS
            role = new Role(authority: 'PENDING_APPROVAL').save(failOnError: true)
            user = new User(
                    username: 'Rodrigo',
                    password: 'a',
                    name: 'Rodrigo',
                    registrationId: "00000000-1",
                    ages: AGES.I,
                    configuration: new UserConfiguration(mode: ThemeMode.DARK)
            ).save(failOnError: true)
            UserRole.create user, role
            UserProject.create user, project

            user = new User(
                    username: 'Amanda',
                    password: 'b',
                    name: 'Amanda',
                    registrationId: "00000000-2",
                    ages: AGES.II,
                    configuration: new UserConfiguration(mode: ThemeMode.DARK)
            ).save(failOnError: true)
            UserRole.create user, role
            UserProject.create user, project

            user = new User(
                    username: 'Renata',
                    password: 'c',
                    name: 'Renata',
                    registrationId: "00000000-3",
                    ages: AGES.III,
                    configuration: new UserConfiguration(mode: ThemeMode.DARK)
            ).save(failOnError: true)
            UserRole.create user, role
//            UserProject.create user, project

            user = new User(
                    username: 'Douglas',
                    password: 'd',
                    name: 'Douglas',
                    registrationId: "00000000-4",
                    ages: AGES.IV,
                    configuration: new UserConfiguration(mode: ThemeMode.DARK)
            ).save(failOnError: true)
            UserRole.create user, role
            UserProject.create user, project



            new Role(authority: 'ROLE_AGESI').save(failOnError: true)
            new Role(authority: 'ROLE_AGESII').save(failOnError: true)
            new Role(authority: 'ROLE_AGESIV').save(failOnError: true)

        }
    }

    def destroy = {}
}
