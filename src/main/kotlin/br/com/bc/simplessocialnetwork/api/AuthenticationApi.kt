package br.com.bc.simplessocialnetwork.api

import br.com.bc.simplessocialnetwork.api.dto.AuthenticatedUserApiDTO
import br.com.bc.simplessocialnetwork.api.dto.CreatedUserApiDTO
import br.com.bc.simplessocialnetwork.usecase.AuthenticateUserUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthenticationApi(
    private val authenticateUserUseCase: AuthenticateUserUseCase
) {
    @PostMapping
    fun authenticate(@RequestBody body: AuthenticatedUserApiDTO): ResponseEntity<CreatedUserApiDTO> {
        val response = authenticateUserUseCase.doAuthenticate(body.email, body.password)

        return ResponseEntity.ok().body(
            response
        )

    }
}