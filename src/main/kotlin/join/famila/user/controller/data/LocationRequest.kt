package join.famila.user.controller.data

import java.math.BigDecimal
import join.famila.user.infrastructure.Location

data class LocationRequest(
    val address: String,

    val latitude: BigDecimal,

    val longitude: BigDecimal,
) {
    fun toEntity(): Location {
        return Location(
            address = address,
            latitude = latitude,
            longitude = longitude,
        )
    }
}
