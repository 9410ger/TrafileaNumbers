package com.trafilea.numbers.trafileanumbers.web.controller

import com.trafilea.numbers.trafileanumbers.exception.NumberTrafileaNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionController: ResponseEntityExceptionHandler() {

    @ExceptionHandler(NumberTrafileaNotFoundException::class)
    fun handleNumberTrafileaNotFound(
        ex: NumberTrafileaNotFoundException,
        request: WebRequest): ResponseEntity<Any>{
        val body = mapOf("message" to ex.message)
        return ResponseEntity(body, HttpStatus.NOT_FOUND)
    }

}