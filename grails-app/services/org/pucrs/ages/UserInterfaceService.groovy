package org.pucrs.ages

import grails.gorm.services.Service

@Service(User)
interface UserInterfaceService {

    User get(Serializable id)

    List<User> list(Map args)

    Long count()

    void delete(Serializable id)

    User save(User userConfiguration)

}