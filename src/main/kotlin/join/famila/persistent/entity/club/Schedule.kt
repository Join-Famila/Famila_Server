package join.famila.persistent.entity.club

import jakarta.persistence.*
import jakarta.persistence.GenerationType.*
import java.time.LocalDateTime
import java.time.LocalDateTime.*

@Entity
@Table
class Schedule(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    val club: Club,

    val startedAt: LocalDateTime,

    val dues: Long,

    val capacity: Long,

    val location: String,

    val createdAt: LocalDateTime = now(),
)
