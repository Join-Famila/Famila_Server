package join.famila.user.controller.data

import java.math.BigDecimal

data class LocationResponse(
    val address: String,

    val latitude: BigDecimal,

    val longitude: BigDecimal,
)
