package org.pucrs.ages

import grails.compiler.GrailsCompileStatic
import groovy.transform.ToString


@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class Project implements Serializable {

    private static final long serialVersionUID = 1

    String projectName
    String semester

    static constraints = {
        projectName nullable: false, blank: false, unique: true
        semester nullable: false, blank: false, matches: "\\d{2}/\\d{1}"
    }

    static mapping = {
        cache true
    }

    String toString() {
        projectName
    }

}
