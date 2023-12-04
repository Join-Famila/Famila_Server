package join.famila.configuration.security

import join.famila.configuration.jwt.JwtAuthenticationFilter
import join.famila.configuration.jwt.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
class SecurityConfiguration(
    private val tokenProvider: TokenProvider,
) {
    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().requestMatchers(
                "/swagger-ui/**",
                "/v3/**",
            )
        }
    }

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.anyRequest().authenticated()
            }
            .sessionManagement { it.sessionCreationPolicy(STATELESS) }
            .addFilterBefore(
                JwtAuthenticationFilter(tokenProvider),
                BasicAuthenticationFilter::class.java,
            )
            .build()
    }

    @Bean
    fun bCryptPasswordEncoder() = BCryptPasswordEncoder()
}
