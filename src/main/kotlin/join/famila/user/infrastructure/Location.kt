package join.famila.user.infrastructure

import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
class Location(
    val address: String,

    val latitude: BigDecimal,   //위도

    val longitude: BigDecimal,  //경도
)
