package join.famila.user.controller.data

import join.famila.club.infrastructure.Category
import java.time.LocalDate

data class UserResponse(
    val id: Long,

    val name: String,

    val profile: String,

    val gender: Gender,

    val phoneNumber: String,

    val location: LocationResponse,

    val birthDay: LocalDate,

    val introduce: String,

    val categories: Set<Category>,
)
