package br.com.bc.simplessocialnetwork.api

import br.com.bc.simplessocialnetwork.api.dto.GetPostApiDTO
import br.com.bc.simplessocialnetwork.api.dto.PostApiDTO
import br.com.bc.simplessocialnetwork.api.dto.PutPostApiDTO
import br.com.bc.simplessocialnetwork.entity.Post
import br.com.bc.simplessocialnetwork.usecase.HandlePostUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/post")
class PostApi(
    private val handlePost: HandlePostUseCase,
) {

    @GetMapping
    fun get(): ResponseEntity<List<GetPostApiDTO>> {
        val result = handlePost.getAll()
        val response = result.map {
            GetPostApiDTO(
                id = it.id!!,
                userId = it.userId.toString(),
                content = it.content!!,
                likes = it.likes!!,
                createdAt = it.createdAt.toString(),
            )
        }
        return ResponseEntity.ok().body(response)
    }

    @GetMapping("/comments/{parentPostId}")
    fun getCommentsPost(@PathVariable parentPostId: Long): ResponseEntity<List<GetPostApiDTO>> {
        val result = handlePost.getComments(parentPostId)
        val response = result.map {
            GetPostApiDTO(
                id = it.id!!,
                userId = it.userId.toString(),
                content = it.content!!,
                likes = it.likes!!,
                createdAt = it.createdAt.toString(),
            )
        }.sortedByDescending { it.createdAt }

        return ResponseEntity.ok().body(response)
    }

    @PostMapping
    fun post(
        @RequestBody body: PostApiDTO,
    ): ResponseEntity<Unit> {
        val createPost = handlePost.create(
            content = body.content,
            userId = body.userId,
        )

        return ResponseEntity.created(URI.create("/post/${createPost.id}")).build()
    }

    @PutMapping
    fun put(@RequestBody body: PutPostApiDTO): ResponseEntity<Unit> {
        handlePost.comment(
            dto = body
        )
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/like/{postId}")
    fun putLike(@PathVariable postId: Long): ResponseEntity<GetPostApiDTO> {
        val result = handlePost.like(postId)

        val response = GetPostApiDTO(
            id = result.id!!,
            userId = result.userId.toString(),
            content = result.content!!,
            likes = result.likes!!,
            createdAt = result.createdAt.toString(),
        )

        return ResponseEntity.ok().body(response)
    }
}