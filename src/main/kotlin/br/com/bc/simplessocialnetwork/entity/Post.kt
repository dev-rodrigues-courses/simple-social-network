package br.com.bc.simplessocialnetwork.entity

import jakarta.persistence.*
import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.FetchType.LAZY
import java.time.LocalDateTime

@Entity(name = "posts")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val userId: Long?,

    val content: String?,

    var likes: Int?,

    val createdAt: LocalDateTime?,

    @OneToMany(mappedBy = "parentPost", cascade = [ALL], fetch = LAZY)
    val comments: MutableList<Post>? = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "parent_post_id")
    val parentPost: Post? = null
)