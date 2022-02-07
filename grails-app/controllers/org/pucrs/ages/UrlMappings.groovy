package org.pucrs.ages

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {}
        }

        "/"(view: '/index')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
