package br.com.bc.simplessocialnetwork.usecase

import br.com.bc.simplessocialnetwork.api.dto.CreatedUserApiDTO
import br.com.bc.simplessocialnetwork.exception.UserNotFoundException
import br.com.bc.simplessocialnetwork.exception.UserPasswordNotMatchException
import br.com.bc.simplessocialnetwork.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class AuthenticateUserUseCase(
    private val userRepository: UserRepository
) {
    fun doAuthenticate(email: String, password: String): CreatedUserApiDTO {
        val user = userRepository.findByEmail(email)

        if (user == null) {
            throw UserNotFoundException(
                message = "User not found",
            )
        }

        if (user.password != password) {
            throw UserPasswordNotMatchException(
                message = "Password does not match",
            )
        }

        return CreatedUserApiDTO(
            id = user.id!!,
            name = user.name!!,
            email = user.email!!
        )
    }
}