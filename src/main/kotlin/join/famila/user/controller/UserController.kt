package join.famila.user.controller

import jakarta.servlet.http.HttpServletResponse
import join.famila.club.infrastructure.Category
import join.famila.club.infrastructure.Tag
import join.famila.user.controller.data.Gender.*
import join.famila.user.controller.data.LocationResponse
import join.famila.user.controller.data.SignInRequest
import join.famila.user.controller.data.SignUpRequest
import join.famila.user.controller.data.UserResponse
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.security.Principal
import java.time.LocalDate

@RestController
@RequestMapping("api/v1/users")
class UserController {
    @GetMapping("me")
    @ResponseStatus(OK)
    fun getMe(principal: Principal): UserResponse {
        return UserResponse(
            id = 1,
            name = "테스트이름",
            profile = "profilePath",
            gender = FEMALE,
            phoneNumber = "01012345678",
            location = LocationResponse(
                address = "서울특별시 구로구 구로동",
                latitude = BigDecimal.valueOf(123.4),
                longitude = BigDecimal.valueOf(233.624),
            ),
            birthDay = LocalDate.of(1991, 9, 25),
            introduce = "안녕하세요. 자기소개 입니다.",
            categories = setOf(
                Category(tag = Tag.BIKING),
                Category(tag = Tag.GOLF),
                Category(tag = Tag.MEDITATION),
            ),
        )
    }

    @PostMapping("signIn")
    @ResponseStatus(OK)
    fun signIn(
        @RequestBody signInRequest: SignInRequest,
        httpServletResponse: HttpServletResponse,
    ): UserResponse {
        httpServletResponse.addHeader("authentication", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxOlVTRVIiLCJpYXQiOjE1MTYyMzkwMjIsImV4cCI6MTUxNjIzOTMyMn0.1JJh--9PzUBQPOzzpSMlhEWs1HuDLq_2NXM5icUgWmo")
        httpServletResponse.addHeader("refreshToken", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxIn0.rTCH8cLoGxAm_xw68z-zXVKi9ie6xJn9tnVWjd_9ftE")

        return UserResponse(
            id = 1,
            name = "테스트이름",
            profile = "profilePath",
            gender = MALE,
            phoneNumber = "01012345678",
            location = LocationResponse(
                address = "서울특별시 구로구 구로동",
                latitude = BigDecimal.valueOf(123.4),
                longitude = BigDecimal.valueOf(233.624),
            ),
            birthDay = LocalDate.of(1991, 9, 25),
            introduce = "안녕하세요. 자기소개 입니다.",
            categories = setOf(
                Category(tag = Tag.BIKING),
                Category(tag = Tag.GOLF),
                Category(tag = Tag.MEDITATION),
            ),
        )
    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun signUp(
        @RequestBody signUpRequest: SignUpRequest,
        httpServletResponse: HttpServletResponse,
    ): UserResponse {
        httpServletResponse.addHeader("authentication", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxOlVTRVIiLCJpYXQiOjE1MTYyMzkwMjIsImV4cCI6MTUxNjIzOTMyMn0.1JJh--9PzUBQPOzzpSMlhEWs1HuDLq_2NXM5icUgWmo")
        httpServletResponse.addHeader("refreshToken", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxIn0.rTCH8cLoGxAm_xw68z-zXVKi9ie6xJn9tnVWjd_9ftE")

        return with(signUpRequest) {
            UserResponse(
                id = 1,
                name = name,
                profile = profile,
                gender = gender,
                phoneNumber = phoneNumber,
                location = LocationResponse(
                    address = location.address,
                    latitude = location.latitude,
                    longitude = location.longitude,
                ),
                birthDay = birthDay,
                introduce = introduce,
                categories = categories,
            )
        }
    }
}
