package com.ig

class Reminder {

    static belongsTo = [task: Task]

    Date reminderDate

    static constraints = {
        reminderDate nullable: false

    }
}
