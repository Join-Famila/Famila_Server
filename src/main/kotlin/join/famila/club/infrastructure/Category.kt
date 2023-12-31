package join.famila.club.infrastructure

import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

@Embeddable
class Category(
    @Enumerated(STRING)
    val tag: Tag,
)
