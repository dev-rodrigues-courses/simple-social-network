package br.com.bc.simplessocialnetwork.usecase

import br.com.bc.simplessocialnetwork.api.dto.PutPostApiDTO
import br.com.bc.simplessocialnetwork.entity.Post
import br.com.bc.simplessocialnetwork.repository.PostRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class HandlePostUseCase(
    private val userUseCase: UserUseCase,
    private val repository: PostRepository,
) {

    fun create(content: String, userId: Long): Post {
        val user = userUseCase.findUserById(userId = userId)

        val post = Post(
            id = null,
            userId = user.id!!,
            content = content,
            likes = 0,
            createdAt = LocalDateTime.now(),
            comments = mutableListOf(),
            parentPost = null,
        )

        return repository.save(post)
    }

    fun comment(dto: PutPostApiDTO): Post {

        val parentPost = repository.findById(dto.parentPostId).orElseThrow { throw Exception("Post not found") }

        parentPost.comments?.add(
            Post(
                id = null,
                userId = dto.userId,
                content = dto.content,
                likes = 0,
                createdAt = LocalDateTime.now(),
                comments = mutableListOf(),
                parentPost = parentPost,
            )
        )

        return repository.save(parentPost)

    }

    fun getAll(): List<Post> {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    fun getComments(parentPostId: Long): List<Post> {
        val parentPost = repository.findById(parentPostId).orElseThrow { throw Exception("Post not found") }
        return parentPost.comments!!
    }

    fun like(postId: Long): Post {
        val parentPost = repository.findById(postId).orElseThrow { throw Exception("Post not found") }
        parentPost.likes = parentPost.likes?.plus(1) ?: 1

        return repository.save(parentPost)
    }
}