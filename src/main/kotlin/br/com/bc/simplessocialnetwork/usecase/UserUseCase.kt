package br.com.bc.simplessocialnetwork.usecase

import br.com.bc.simplessocialnetwork.entity.User
import br.com.bc.simplessocialnetwork.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserUseCase(
    private val userRepository: UserRepository,
) {

    fun findUserByEmail(email: String): User {
        val user = userRepository.findByEmail(email)

        if (user == null) {
            throw IllegalArgumentException("User not found")
        }

        return user
    }

    fun findUserById(userId: Long): User {
        val user = userRepository.findById(userId)


        if (!user.isPresent) {
            throw IllegalArgumentException("User not found")
        }

        return user.get()
    }
}