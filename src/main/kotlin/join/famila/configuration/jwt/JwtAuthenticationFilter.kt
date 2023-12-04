package join.famila.configuration.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(
    private val tokenProvider: TokenProvider,
) : OncePerRequestFilter() {
    private val IDENTIFY: String = "identify"

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token = request.getHeader(AUTHORIZATION)?.substring(7) ?: throw NullPointerException()
        val identify = getCookieValueByKey(cookies = request.cookies, key = IDENTIFY)
        val user = parseIdentificationInformation(token, identify)

        UsernamePasswordAuthenticationToken(user, token, user.authorities)
            .apply {
                details = WebAuthenticationDetails(request)
                SecurityContextHolder.getContext().authentication = this
            }

        filterChain.doFilter(request, response)
    }

    private fun getCookieValueByKey(cookies: Array<Cookie>, key: String): String {
        return cookies.associate { it.name to it.value }[key] ?: ""
    }

    private fun parseIdentificationInformation(token: String, identify: String): User {
        return tokenProvider.getSubject(token = token, identify = identify)
            .split(":")
            .let { User(it[0], "", listOf(SimpleGrantedAuthority(it[1]))) }
    }
}
