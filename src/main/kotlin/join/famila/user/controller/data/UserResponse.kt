package join.famila.user.controller.data

import join.famila.club.infrastructure.Category
import join.famila.user.infrastructure.Gender
import join.famila.user.infrastructure.User
import java.time.LocalDate

data class UserResponse(
    val id: Long,

    val name: String,

    val profile: ByteArray?,

    val gender: Gender,

    val phoneNumber: String,

    val location: LocationResponse,

    val birthDay: LocalDate,

    val introduce: String?,

    val categories: Set<Category>,
) {
    constructor(user: User, profile: ByteArray?) : this(
        id = user.id,
        name = user.name,
        profile = profile,
        gender = user.gender,
        phoneNumber = user.phoneNumber,
        location = user.location.let(::LocationResponse),
        birthDay = user.birthDay,
        introduce = user.introduce,
        categories = user.categories,
    )
}
