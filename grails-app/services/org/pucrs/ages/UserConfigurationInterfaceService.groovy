package org.pucrs.ages

import grails.gorm.services.Service

@Service(UserConfiguration)
interface UserConfigurationInterfaceService {

    UserConfiguration get(Serializable id)

    List<UserConfiguration> list(Map args)

    Long count()

    void delete(Serializable id)

    UserConfiguration save(UserConfiguration userConfiguration)

}