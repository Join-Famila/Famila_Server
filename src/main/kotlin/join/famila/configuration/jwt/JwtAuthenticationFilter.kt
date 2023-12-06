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
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token = request.getHeader(AUTHORIZATION)?.substring(7) ?: throw NullPointerException()
        val identifyCode = getIdentifyCode(cookies = request.cookies)
        val user = parseIdentificationInformation(token = token, identifyCode = identifyCode)

        UsernamePasswordAuthenticationToken(user, token, user.authorities)
            .apply {
                details = WebAuthenticationDetails(request)
                SecurityContextHolder.getContext().authentication = this
            }

        filterChain.doFilter(request, response)
    }

    private fun getIdentifyCode(cookies: Array<Cookie>): String {
        return cookies.associate { it.name to it.value }[IDENTIFY_CODE]
            ?: throw NullPointerException()
    }

    private fun parseIdentificationInformation(token: String, identifyCode: String): User {
        return tokenProvider.getSubject(token = token, identifyCode = identifyCode)
            .split(":")
            .let { User(it[0], "", listOf(SimpleGrantedAuthority(it[1]))) }
    }

    companion object {
        private const val IDENTIFY_CODE = "identify_code"
    }
}
