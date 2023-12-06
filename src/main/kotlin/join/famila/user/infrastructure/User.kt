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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import join.famila.club.infrastructure.Category
import join.famila.user.controller.data.SignUpRequest
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

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

    val birthDay: LocalDate,

    val introduce: String,

    @CollectionTable
    @ElementCollection
    val categories: Set<Category> = setOf(),

    @CreatedDate
    val createdAt: LocalDateTime = now(),

    @LastModifiedDate
    val updatedAt: LocalDateTime? = null,

    val deletedAt: LocalDateTime? = null,
) {
    companion object {
        fun of(request: SignUpRequest): User {
            return with(request) {
                User(
                    name = name,
                    identifier = setOf(
                        Identifier(
                            uid = uid,
                            provider = provider,
                        ),
                    ),
                    profile = TODO("s3Service를 사용하여 업로드 할 예정"),
                    gender = gender,
                    phoneNumber = phoneNumber,
                    location = Location(
                        address = location.address,
                        latitude = location.latitude,
                        longitude = location.longitude,
                    ),
                    birthDay = birthDay,
                    introduce = introduce,
                    categories = categories,
                )
            }
        }
    }
}
