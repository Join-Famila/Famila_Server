package join.famila.configuration.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime.now
import java.util.Date.from
import javax.crypto.spec.SecretKeySpec
import join.famila.configuration.jwt.Role.USER
import join.famila.user.infrastructure.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TokenProvider(
    @Value("\${jwt.secret-key}")
    private val secretKey: String,

    @Value("\${jwt.issuer}")
    private val issuer: String,

    @Value("\${jwt.expiration-second}")
    private val expirationSecond: Long,
) {
    private val IDENTIFY: String = "identify"

    fun createToken(user: User, uuid: String): String {
        return user.let {
            Jwts.builder()
                .subject(getIdentificationInformation(it))
                .claim("identify", uuid)
                .issuer(issuer)
                .issuedAt(Timestamp.valueOf(now()))
                .expiration(from(Instant.now().plusSeconds(expirationSecond)))
                .signWith(SecretKeySpec(secretKey.toByteArray(), SignatureAlgorithm.HS512.jcaName))
                .compact()!!
        }
    }

    private fun getIdentificationInformation(user: User) =
        "${user.id}:$USER"

    fun getSubject(token: String, identify: String): String {
        return getClaims(token = token).also {
            check(it[IDENTIFY].toString() == identify)
        }.subject
    }

    private fun getClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(SecretKeySpec(secretKey.toByteArray(), SignatureAlgorithm.HS512.jcaName))
            .build()
            .parseSignedClaims(token)
            .payload
    }
}
