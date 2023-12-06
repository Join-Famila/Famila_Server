package join.famila.user.controller.data

import java.time.LocalDate
import join.famila.club.infrastructure.Category
import join.famila.user.infrastructure.Gender
import org.springframework.web.multipart.MultipartFile

data class SignUpRequest(
    val uid: String,

    val provider: String,

    val name: String,

    val profile: MultipartFile,

    val gender: Gender,

    val phoneNumber: String,

    val location: LocationRequest,

    val birthDay: LocalDate,

    val introduce: String,

    val categories: Set<Category>,
)
