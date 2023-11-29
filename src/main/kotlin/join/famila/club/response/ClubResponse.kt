package join.famila.club.response

import java.time.LocalDateTime
import join.famila.club.infrastructure.Club

class ClubResponse(
    val id: Long,

    val name: String,

    val introduce: String,

    val members: List<MemberResponse>,

    val posts: List<PostResponse>,

    val schedules: List<ScheduleResponse>,

    val categories: List<CategoryResponse>,

    val createdAt: LocalDateTime,
) {
    constructor(club: Club) : this(
        id = club.id,
        name = club.name,
        introduce = club.introduce,
        members = club.members.map(::MemberResponse),
        posts = club.posts.map(::PostResponse),
        schedules = club.schedules.map(::ScheduleResponse),
        categories = club.categories.map(::CategoryResponse),
        createdAt = club.createdAt,
    )
}
