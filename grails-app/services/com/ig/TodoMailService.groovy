package com.ig

import grails.transaction.Transactional

class TodoMailService {

    def mailService

    def sendMail(String receiver, String title, String body) {
        mailService.sendMail {
            from "app@todo.com"
            replyTo "app@todo.com"
            to receiver

            subject title
            html body
        }

    }

}
