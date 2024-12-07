package br.com.bc.simplessocialnetwork.usecase

import br.com.bc.simplessocialnetwork.api.dto.UserApiDTO
import br.com.bc.simplessocialnetwork.entity.User
import br.com.bc.simplessocialnetwork.exception.ValidationException
import br.com.bc.simplessocialnetwork.mapper.UserMapper
import br.com.bc.simplessocialnetwork.repository.UserRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component

@Component
class CreateUserUseCase(
    private val userRepository: UserRepository
) {

    fun doCreate(dto: UserApiDTO): User {
        try {
            val existingUser = userRepository.findByEmail(dto.email)

            if (existingUser != null) {
                throw ValidationException(
                    message = "User with email ${dto.email} already exists"
                )
            }


        } catch (e: EmptyResultDataAccessException) {

        }

        return userRepository.save(UserMapper.mapToEntity(dto))
    }
}