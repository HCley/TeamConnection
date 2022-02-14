package org.pucrs.ages

class UserConfiguration {

    ThemeMode mode

    static belongsTo = [user: User]

    static constraints = {
        user nullable: false, blank: false, unique: true
        mode nullable: false, blank: false
    }

    static mapping = {
        mode cache: true
    }

    @Override
    String toString() {
        """User: ${user.getName()}
            mode: ${mode}"""
    }
}
