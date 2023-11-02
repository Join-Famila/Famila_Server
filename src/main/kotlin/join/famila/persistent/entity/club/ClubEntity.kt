package join.famila.persistent.entity.club

import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity
@Table(name = "club")
class ClubEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,

    val leaderId: Long,

    val introduce: String,

    @OneToMany(mappedBy = "club", cascade = [CascadeType.ALL], orphanRemoval = true)
    val members: List<MemberEntity> = listOf(),

    @OneToMany(mappedBy = "club", cascade = [CascadeType.ALL], orphanRemoval = true)
    val posts: List<PostEntity> = listOf(),

    @OneToMany(mappedBy = "club", cascade = [CascadeType.ALL], orphanRemoval = true)
    val pictures: List<PictureEntity> = listOf(),

    @OneToMany(mappedBy = "club", cascade = [CascadeType.ALL], orphanRemoval = true)
    val schedules: List<ScheduleEntity> = listOf(),

    @CollectionTable(
        name = "category",
        joinColumns = [JoinColumn(name = "club_id", referencedColumnName = "category_id")],
    )
    @ElementCollection
    val categories: List<CategoryEntity> = listOf(),

    val createdAt: LocalDateTime = now(),

    val updatedAt: LocalDateTime? = null,

    val deletedAt: LocalDateTime? = null,
)
