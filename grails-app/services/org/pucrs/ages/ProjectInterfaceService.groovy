package org.pucrs.ages

import grails.gorm.services.Service

@Service(Questions)
interface ProjectInterfaceService {

    Project get(Serializable id)

    List<Project> list(Map args)

    Long count()

    void delete(Serializable id)

    Project save(Project questions)

}