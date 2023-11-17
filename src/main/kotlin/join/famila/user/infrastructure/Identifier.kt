package join.famila.user.infrastructure

import jakarta.persistence.Embeddable

@Embeddable
class Identifier(
    val provider: String,

    val uid: String,
)
