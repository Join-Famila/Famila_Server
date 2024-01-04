package join.famila.user.controller.data

import java.math.BigDecimal
import join.famila.user.infrastructure.Location

data class LocationResponse(
    val address: String,

    val latitude: BigDecimal,

    val longitude: BigDecimal,
) {
    constructor(location: Location) : this(
        address = location.address,
        latitude = location.latitude,
        longitude = location.longitude,
    )
}
