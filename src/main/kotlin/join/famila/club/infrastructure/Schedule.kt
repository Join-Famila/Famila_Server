package join.famila.club.infrastructure

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity
class Schedule(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    val club: Club,

    var startedAt: LocalDateTime,

    var dues: Long,

    var capacity: Long,

    var location: String,

    @CreatedDate
    val createdAt: LocalDateTime = now(),

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null,
)
