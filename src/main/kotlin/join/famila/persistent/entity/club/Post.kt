package join.famila.persistent.entity.club

import jakarta.persistence.*
import jakarta.persistence.GenerationType.*
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity
@Table
class Post(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    val club: Club,

    val memberId: Long,

    val title: String,

    val content: String,

    val isNotification: Boolean,

    val createdAt: LocalDateTime = now(),

    val updatedAt: LocalDateTime? = null,

    val deletedAt: LocalDateTime? = null,
)
