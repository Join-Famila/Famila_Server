package join.famila.persistent.entity.club

import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

@Embeddable
@Table(name = "category")
class CategoryEntity(
    @Enumerated(STRING)
    val tag: Tag,
)
