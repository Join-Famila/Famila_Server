package join.famila.persistent.entity.club

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity
@Table(name = "picture")
class PictureEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    val club: ClubEntity,

    val memberId: Long,

    val link: String,

    val createdAt: LocalDateTime = now(),

    val updatedAt: LocalDateTime? = null,

    val deletedAt: LocalDateTime? = null,
)
