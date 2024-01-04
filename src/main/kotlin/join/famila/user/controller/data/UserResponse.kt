package join.famila.user.controller.data

import java.time.LocalDate
import join.famila.club.infrastructure.Category
import join.famila.user.infrastructure.Gender
import join.famila.user.infrastructure.User

data class UserResponse(
    val id: Long,

    val name: String,

    val profile: String,

    val gender: Gender,

    val phoneNumber: String,

    val location: LocationResponse,

    val birthDay: LocalDate,

    val introduce: String?,

    val categories: Set<Category>,
) {
    constructor(user: User) : this(
        id = user.id,
        name = user.name,
        profile = user.profile,
        gender = user.gender,
        phoneNumber = user.phoneNumber,
        location = user.location.let(::LocationResponse),
        birthDay = user.birthDay,
        introduce = user.introduce,
        categories = user.categories,
    )
}
