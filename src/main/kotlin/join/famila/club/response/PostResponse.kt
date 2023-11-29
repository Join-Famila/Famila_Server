package join.famila.club.response

import java.time.LocalDateTime
import join.famila.club.infrastructure.Post

class PostResponse(
    val id: Long,

    val clubId: Long,

    val memberId: Long,

    val title: String,

    val content: String,

    val isNotification: Boolean,

    val createdAt: LocalDateTime,

    val updatedAt: LocalDateTime?,
) {
    constructor(post: Post) : this(
        id = post.id,
        clubId = post.club.id,
        memberId = post.memberId,
        title = post.title,
        content = post.content,
        isNotification = post.isNotification,
        createdAt = post.createdAt,
        updatedAt = post.updatedAt,
    )
}
