package br.com.bc.simplessocialnetwork.api.validation

import br.com.bc.simplessocialnetwork.exception.UserPasswordNotMatchException
import br.com.bc.simplessocialnetwork.exception.ValidationException
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiControllerAdvice {
    @ResponseBody
    @ExceptionHandler(ValidationException::class)
    @ResponseStatus(BAD_REQUEST)
    fun handle(e: ValidationException): Response {
        return Response(e.message)
    }

    @ResponseBody
    @ExceptionHandler(UserPasswordNotMatchException::class)
    @ResponseStatus(BAD_REQUEST)
    fun handle(e: UserPasswordNotMatchException): Response {
        return Response(e.message)
    }
}

data class Response(val message: String)