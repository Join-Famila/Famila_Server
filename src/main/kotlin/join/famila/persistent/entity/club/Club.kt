package join.famila.persistent.entity.club

import jakarta.persistence.CascadeType
import jakarta.persistence.CollectionTable
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity
class Club(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,

    val leaderId: Long,

    val introduce: String,

    @OneToMany(mappedBy = "club", cascade = [CascadeType.ALL], orphanRemoval = true)
    val members: List<Member> = listOf(),

    @OneToMany(mappedBy = "club", cascade = [CascadeType.ALL], orphanRemoval = true)
    val posts: List<Post> = listOf(),

    @OneToMany(mappedBy = "club", cascade = [CascadeType.ALL], orphanRemoval = true)
    var pictures: List<Picture> = listOf(),

    @OneToMany(mappedBy = "club", cascade = [CascadeType.ALL], orphanRemoval = true)
    val schedules: List<Schedule> = listOf(),

    @CollectionTable(
        name = "category",
        joinColumns = [JoinColumn(name = "club_id", referencedColumnName = "category_id")],
    )
    @ElementCollection
    val categories: List<Category> = listOf(),

    @CreatedDate
    val createdAt: LocalDateTime = now(),

    @LastModifiedDate
    val updatedAt: LocalDateTime? = null,

    val deletedAt: LocalDateTime? = null,
)
