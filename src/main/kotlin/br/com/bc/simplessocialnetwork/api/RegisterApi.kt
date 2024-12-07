package br.com.bc.simplessocialnetwork.api

import br.com.bc.simplessocialnetwork.api.dto.CreatedUserApiDTO
import br.com.bc.simplessocialnetwork.api.dto.UserApiDTO
import br.com.bc.simplessocialnetwork.usecase.CreateUserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/register")
class RegisterApi(
    private val createUserUseCase: CreateUserUseCase
) {

    @PostMapping
    fun register(@RequestBody dto: UserApiDTO): ResponseEntity<CreatedUserApiDTO> {
        println("Registering user: $dto")
        val created = createUserUseCase.doCreate(dto)
        return ResponseEntity.ok().body(
            CreatedUserApiDTO(
                id = created.id!!,
                name = created.name!!,
                email = created.email!!
            )
        )
    }
}