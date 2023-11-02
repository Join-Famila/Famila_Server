package join.famila.persistent.entity.club

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity
@Table(name = "member")
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    val club: ClubEntity,

    val userId: Long,

    val createdAt: LocalDateTime = now(),

    val deletedAt: LocalDateTime? = null,
)
