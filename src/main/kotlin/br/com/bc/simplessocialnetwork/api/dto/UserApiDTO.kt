package br.com.bc.simplessocialnetwork.api.dto

data class UserApiDTO (
    val name: String,
    val email: String,
    val password: String
)

data class CreatedUserApiDTO (
    val id: Long,
    val name: String,
    val email: String
)