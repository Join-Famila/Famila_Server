package join.famila.user.controller.data

import join.famila.club.infrastructure.Category
import java.time.LocalDate

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
)
