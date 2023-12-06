package join.famila.user.controller

import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import java.security.Principal
import java.util.UUID.randomUUID
import join.famila.user.controller.data.SignInRequest
import join.famila.user.controller.data.SignUpRequest
import join.famila.user.controller.data.UserResponse
import join.famila.user.service.UserService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/users")
class UserController(
    private val userService: UserService,
) {
    @GetMapping("me")
    @ResponseStatus(OK)
    @Tag(name = "로그인 사용자 정보 가져오는 API", description = "헤더로 전달 받은 Authentication 값을 바탕으로 사용자 정보를 가져옴")
    fun getMe(principal: Principal): UserResponse {
        return userService.get(id = principal.name.toLong()).let(::UserResponse)
    }

    @PostMapping("signIn")
    @ResponseStatus(OK)
    @Tag(name = "로그인 API", description = "헤더로 Authentication, refreshToken과 사용자 정보를 전달")
    fun signIn(
        @RequestBody signInRequest: SignInRequest,
        httpServletResponse: HttpServletResponse,
    ): UserResponse {
        httpServletResponse.apply {
            addHeader(
                "Authentication",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxOlVTRVIiLCJpYXQiOjE1MTYyMzkwMjIsImV4cCI6MTUxNjIzOTMyMn0.1JJh--9PzUBQPOzzpSMlhEWs1HuDLq_2NXM5icUgWmo"
            )
            addCookie(
                Cookie("identifyCode", randomUUID().toString()).apply { isHttpOnly = true }
            )
        }

        return userService.get(request = signInRequest).let(::UserResponse)
    }

    @PostMapping("refresh")
    @ResponseStatus(OK)
    @Tag(name = "토큰 재발급 API", description = "헤더로 Authentication를 전달하면 토큰이 재발급됨")
    fun refresh(
        httpServletResponse: HttpServletResponse,
    ) {
        httpServletResponse.apply {
            addHeader(
                "Authentication",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxOlVTRVIiLCJpYXQiOjE1MTYyMzkwMjIsImV4cCI6MTUxNjIzOTMyMn0.1JJh--9PzUBQPOzzpSMlhEWs1HuDLq_2NXM5icUgWmo"
            )
            addCookie(
                Cookie("identifyCode", randomUUID().toString()).apply { isHttpOnly = true }
            )
        }
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Tag(name = "회원가입 API", description = "헤더로 Authentication, refreshToken 과 사용자 정보를 전달")
    fun signUp(
        @RequestBody signUpRequest: SignUpRequest,
        httpServletResponse: HttpServletResponse,
    ): UserResponse {
        httpServletResponse.apply {
            addHeader(
                "Authentication",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxOlVTRVIiLCJpYXQiOjE1MTYyMzkwMjIsImV4cCI6MTUxNjIzOTMyMn0.1JJh--9PzUBQPOzzpSMlhEWs1HuDLq_2NXM5icUgWmo"
            )
            addCookie(
                Cookie("identifyCode", randomUUID().toString()).apply { isHttpOnly = true }
            )
        }

        return userService.save(user = signUpRequest.toEntity()).let(::UserResponse)
    }
}
