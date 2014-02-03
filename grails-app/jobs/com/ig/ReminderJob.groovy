package com.ig

import groovy.time.TimeCategory


class ReminderJob {

    def todoMailService

    static triggers = {
      simple repeatInterval: 5000l // execute job once in 5 seconds
    }

    def execute() {
        println " ----------- RUNNING REMINDER JOB ----------- "
        use(TimeCategory) {
            Reminder.findByReminderDateBetween(getStartPeriod(), getEndPeriod()).each {
                println "Found Reminder"
                todoMailService.sendMail(it.task.user.email,  "Task ${it.task.title} is due today",it.task.description)
            }
        }


    }

    Date getStartPeriod() {
        Date date = new Date()
        date.clearTime()
        date
    }

    Date getEndPeriod() {
        use(TimeCategory) {
            new Date() + 1.day - 1.millisecond
        }
    }

}
