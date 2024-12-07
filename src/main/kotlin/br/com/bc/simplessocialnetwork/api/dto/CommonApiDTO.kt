package br.com.bc.simplessocialnetwork.api.dto

data class AuthenticatedUserApiDTO(
    val email: String,
    val password: String
)

data class PostApiDTO(
    val content: String,
    val userId: Long,
)

data class PutPostApiDTO(
    val parentPostId: Long,
    val content: String,
    val userId: Long,
)

data class GetPostApiDTO(
    val id: Long,
    val userId: String,
    val content: String,
    val likes: Int,
    val createdAt: String,
)