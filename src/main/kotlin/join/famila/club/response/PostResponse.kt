package join.famila.club.response

import java.time.LocalDateTime
import join.famila.club.infrastructure.Post

class PostResponse(post: Post) {
    val id: Long

    val clubId: Long

    val memberId: Long

    val title: String

    val content: String

    val isNotification: Boolean

    val createdAt: LocalDateTime

    val updatedAt: LocalDateTime?

    init {
        id = post.id
        clubId = post.club.id
        memberId = post.memberId
        title = post.title
        content = post.content
        isNotification = post.isNotification
        createdAt = post.createdAt
        updatedAt = post.updatedAt
    }
}
