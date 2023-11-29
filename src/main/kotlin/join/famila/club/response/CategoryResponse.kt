package join.famila.club.response

import join.famila.club.infrastructure.Category

class CategoryResponse(
    val tag: String,
) {
    constructor(category: Category) : this(
        tag = category.tag.name
    )
}
