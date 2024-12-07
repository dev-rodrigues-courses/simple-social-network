package br.com.bc.simplessocialnetwork.exception

data class ValidationException (
    override val message: String
) : RuntimeException(message)

data class UserNotFoundException (
    override val message: String
) : RuntimeException(message)

data class UserPasswordNotMatchException (
    override val message: String
) : RuntimeException(message)