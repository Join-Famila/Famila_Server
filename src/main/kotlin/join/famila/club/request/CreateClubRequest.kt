package join.famila.club.request

import join.famila.club.infrastructure.Category

class CreateClubRequest(
    val userId: Long,

    val name: String,

    val introduce: String,

    val categories: List<Category>,
)
