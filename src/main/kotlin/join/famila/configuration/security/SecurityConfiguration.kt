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
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


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
                "/api/v1/users/signUp",
                "/api/v1/users/signIn",
            )
        }
    }

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf { it.disable() }
            .cors { }
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
    fun corsConfigurationSource(): CorsConfigurationSource {
        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration(
                "/**",
                CorsConfiguration().apply {
                    allowCredentials = true
                    allowedOrigins = listOf("*")
                    allowedMethods = listOf("*")
                    allowedHeaders = listOf("*")
                    exposedHeaders = listOf("*")
                }
            )
        }
    }

    @Bean
    fun bCryptPasswordEncoder() = BCryptPasswordEncoder()
}
