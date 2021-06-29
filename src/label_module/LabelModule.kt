package com.example.label_module



import io.ktor.application.*
import io.ktor.routing.*


fun Application.label() {

    routing {
        labelNum()
        labels()
    }
}