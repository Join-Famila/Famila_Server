package join.famila.persistent.entity.club

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity
@Table
class Picture(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    val club: Club,

    val memberId: Long,

    val link: String,

    val createdAt: LocalDateTime = now(),

    val updatedAt: LocalDateTime? = null,

    val deletedAt: LocalDateTime? = null,
)
