package join.famila.user.infrastructure

import jakarta.persistence.Embeddable

@Embeddable
class Identifier(
    val provider: String,

    val uid: String,
) {
    override fun hashCode() = (provider + uid).hashCode()

    override fun equals(other: Any?): Boolean {
        if (other is Identifier) {
            return hashCode() == other.hashCode()
        }

        return false
    }
}
