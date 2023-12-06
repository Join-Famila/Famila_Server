package join.famila.user.controller.data

import java.time.LocalDate
import join.famila.club.infrastructure.Category
import join.famila.user.infrastructure.Gender
import join.famila.user.infrastructure.Identifier
import join.famila.user.infrastructure.Location
import join.famila.user.infrastructure.User

data class SignUpRequest(
    val uid: String,

    val provider: String,

    val name: String,

    val profile: String,

    val gender: Gender,

    val phoneNumber: String,

    val location: LocationRequest,

    val birthDay: LocalDate,

    val introduce: String,

    val categories: Set<Category>,
) {
    fun toEntity(): User {
        return User(
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
