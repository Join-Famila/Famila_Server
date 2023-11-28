package join.famila.club.response

import join.famila.club.infrastructure.Category

class CategoryResponse(category: Category) {
    val tag: String

    init {
        tag = category.tag.name
    }
}
