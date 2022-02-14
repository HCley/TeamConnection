package org.pucrs.ages

import grails.compiler.GrailsCompileStatic
import grails.gorm.DetachedCriteria
import groovy.transform.ToString
import org.codehaus.groovy.util.HashCodeHelper

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class UserProject implements Serializable {

    private static final long serialVersionUID = 1

    User user
    Project project

    @Override
    boolean equals(other) {
        if (other instanceof UserProject) {
            other.userId == user?.id && other.projectId == project?.id
        }
    }

    @Override
    int hashCode() {
        int hashCode = HashCodeHelper.initHash()
        if (user) {
            hashCode = HashCodeHelper.updateHash(hashCode, user.id)
        }
        if (project) {
            hashCode = HashCodeHelper.updateHash(hashCode, project.id)
        }
        hashCode
    }

    static UserProject get(long userId, long projectId) {
        criteriaFor(userId, projectId).get()
    }

    static boolean exists(long userId, long projectId) {
        criteriaFor(userId, projectId).count()
    }

    private static DetachedCriteria criteriaFor(long userId, long projectId) {
        where {
            user == User.load(userId) &&
                    project == Project.load(projectId)
        }
    }

    static UserProject create(User user, Project project, boolean flush = false) {
        def instance = new UserProject(user: user, project: project)
        instance.save(flush: flush)
        instance
    }

    static boolean remove(User u, Project r) {
        if (u != null && r != null) {
            where { user == u && project == r }.deleteAll()
        }
    }

    static int removeAll(User u) {
        u == null ? 0 : where { user == u }.deleteAll() as int
    }

    static int removeAll(Project r) {
        r == null ? 0 : where { project == r }.deleteAll() as int
    }

    static constraints = {
        user nullable: false
        project nullable: false, validator: { Project r, UserProject ur ->
            if (ur.user?.id) {
                if (exists(ur.user.id, r.id)) {
                    return ['userRole.exists']
                }
            }
        }
    }

    static mapping = {
        id composite: ['user', 'project']
        version false
    }
}