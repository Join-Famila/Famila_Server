package join.famila.user.infrastructure

import jakarta.persistence.CollectionTable
import jakarta.persistence.ElementCollection
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity
class User(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,

    val name: String,

    @CollectionTable
    @ElementCollection
    val identifier: Set<Identifier> = setOf(),

    val profile: String,

    @Enumerated(STRING)
    val gender: Gender,

    val phoneNumber: String,

    @Embedded
    val location: Location,

    val age: Int,

    val introduce: String,

    @CollectionTable
    @ElementCollection
    val categories: Set<CharCategory> = setOf(),

    @CreatedDate
    val createdAt: LocalDateTime = now(),

    @LastModifiedDate
    val updatedAt: LocalDateTime? = null,

    val deletedAt: LocalDateTime? = null,
)
