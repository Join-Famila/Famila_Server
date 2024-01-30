package join.famila.user.controller.data

import join.famila.club.infrastructure.Category
import join.famila.user.infrastructure.Gender
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile
import java.math.BigDecimal
import java.time.LocalDate

data class SignUpUserRequest(
    val uid: String,

    val provider: String,

    val name: String,

    val gender: Gender,

    val phoneNumber: String,

    val address: String,

    val latitude: BigDecimal,

    val longitude: BigDecimal,

    val birthDay: LocalDate,

    val introduce: String?,

    val categories: Set<Category>,
)
