package br.com.bc.simplessocialnetwork.repository

import br.com.bc.simplessocialnetwork.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<User, Long> {
    @Query("SELECT u FROM users u WHERE LOWER(u.email) = LOWER(:email)")
    fun findByEmail(@Param("email") email: String): User?
}