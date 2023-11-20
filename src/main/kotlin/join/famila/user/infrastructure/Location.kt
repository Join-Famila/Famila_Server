package join.famila.user.infrastructure

import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
class Location(
    val address: String,

    val latitude: BigDecimal,

    val longitude: BigDecimal,
)
