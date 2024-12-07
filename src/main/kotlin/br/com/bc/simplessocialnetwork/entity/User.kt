package br.com.bc.simplessocialnetwork.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Entity(name = "users")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @field:NotBlank
    @field:Size(min = 3, max = 255)
    val name: String?,

    @field:NotBlank
    @field:Email
    @Column(unique = true)
    val email: String?,

    @field:NotBlank
    @field:Size(min = 3, max = 20)
    val password: String?
)