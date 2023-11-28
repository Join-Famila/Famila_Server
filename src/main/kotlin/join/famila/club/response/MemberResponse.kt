package join.famila.club.response

import java.time.LocalDateTime
import join.famila.club.infrastructure.Member
import join.famila.club.infrastructure.Role

class MemberResponse(member: Member) {
    val id: Long

    val clubId: Long

    val userId: Long

    val role: Role

    val createdAt: LocalDateTime

    init {
        id = member.id
        clubId = member.club.id
        userId = member.userId
        role = member.role
        createdAt = member.createdAt
    }
}
