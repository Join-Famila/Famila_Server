package join.famila.persistent.entity.club

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    val club: Club,

    val userId: Long,

    @CreatedDate
    val createdAt: LocalDateTime = now(),
)
