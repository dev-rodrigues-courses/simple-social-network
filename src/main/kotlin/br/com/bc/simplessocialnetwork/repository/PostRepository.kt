package br.com.bc.simplessocialnetwork.repository

import br.com.bc.simplessocialnetwork.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long> {
    fun findAllByOrderByCreatedAtDesc(): List<Post>
}