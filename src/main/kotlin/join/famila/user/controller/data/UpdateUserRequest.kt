package join.famila.user.controller.data

import join.famila.club.infrastructure.Category
import org.springframework.web.multipart.MultipartFile

data class UpdateUserRequest(
    val profile: MultipartFile?,

    val phoneNumber: String,

    val location: LocationRequest,

    val introduce: String,

    val categories: Set<Category>,
)
