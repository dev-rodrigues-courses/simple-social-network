package br.com.bc.simplessocialnetwork.mapper

import br.com.bc.simplessocialnetwork.api.dto.UserApiDTO
import br.com.bc.simplessocialnetwork.entity.User

class UserMapper {
    companion object {
        fun mapToEntity(dto: UserApiDTO): User {
            return User(
                id = null,
                name = dto.name,
                email = dto.email,
                password = dto.password
            )
        }
    }
}