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
import join.famila.club.infrastructure.Category
import join.famila.user.controller.data.SignUpUserRequest
import join.famila.user.controller.data.UpdateUserRequest
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDate
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

    var profile: String?,

    @Enumerated(STRING)
    val gender: Gender,

    var phoneNumber: String,

    @Embedded
    var location: Location,

    val birthDay: LocalDate,

    var introduce: String? = null,

    @CollectionTable
    @ElementCollection
    var categories: Set<Category> = setOf(),

    @CreatedDate
    val createdAt: LocalDateTime = now(),

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null,

    val deletedAt: LocalDateTime? = null,
) {
    fun update(request: UpdateUserRequest, profile: String?) {
        if (profile != null) {
            this.profile = profile
        }
        phoneNumber = request.phoneNumber
        location = Location(
            address = request.address,
            latitude = request.latitude,
            longitude = request.longitude,
        )
        introduce = request.introduce
        categories = request.categories
        updatedAt = now()
    }

    companion object {
        fun of(request: SignUpUserRequest, profile: String?): User {
            return with(request) {
                User(
                    name = name,
                    identifier = setOf(
                        Identifier(
                            uid = uid,
                            provider = provider,
                        ),
                    ),
                    profile = profile,
                    gender = gender,
                    phoneNumber = phoneNumber,
                    location = Location(
                        address = address,
                        latitude = latitude,
                        longitude = longitude,
                    ),
                    birthDay = birthDay,
                    introduce = introduce,
                    categories = categories,
                )
            }
        }
    }
}
