package join.famila.user.controller.data

import join.famila.club.infrastructure.Category
import java.math.BigDecimal

data class UpdateUserRequest(
    val phoneNumber: String,

    val address: String,

    val latitude: BigDecimal,

    val longitude: BigDecimal,

    val introduce: String?,

    val categories: Set<Category>,
)
