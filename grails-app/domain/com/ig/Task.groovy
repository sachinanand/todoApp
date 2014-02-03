package com.ig

class Task {

    static hasMany=[reminders:Reminder]
    static belongsTo = [user:User]

    String title

    String description
    Date dueDate

    // managed by grails
    Date dateCreated
    Date lastUpdated

    static constraints = {
        title nullable: false, blank: false
        description nullable: true, blank: true
        dueDate nullable: true

    }

    static mapping = {

    }
}
