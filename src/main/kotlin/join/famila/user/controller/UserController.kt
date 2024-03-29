package join.famila.user.controller

import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import join.famila.configuration.jwt.TokenProvider
import join.famila.user.controller.data.SignInUserRequest
import join.famila.user.controller.data.SignUpUserRequest
import join.famila.user.controller.data.UpdateUserRequest
import join.famila.user.controller.data.UserResponse
import join.famila.user.service.FileSave
import join.famila.user.service.ProfileDirectStorageService
import join.famila.user.service.UserService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.security.Principal
import java.util.UUID.randomUUID

@RestController
@RequestMapping("api/v1/users")
class UserController(
    private val userService: UserService,

    private val profileDirectStorageService: FileSave,

    private val tokenProvider: TokenProvider,
) {
    @GetMapping("me")
    @ResponseStatus(OK)
    @Tag(name = "로그인 사용자 정보 가져오는 API", description = "헤더로 전달 받은 Authentication 값을 바탕으로 사용자 정보를 가져옴")
    fun getMe(principal: Principal): UserResponse {
        return userService.get(id = principal.name.toLong()).let {
            val imageByteArray = it.profile?.let { profile ->
                profileDirectStorageService is ProfileDirectStorageService
                profileDirectStorageService.get(profile)
            }

            UserResponse(user = it, profile = imageByteArray)
        }
    }

    @PostMapping("signIn")
    @ResponseStatus(OK)
    @Tag(name = "로그인 API", description = "헤더로 Authentication, refreshToken과 사용자 정보를 전달")
    fun signIn(
        @RequestBody signInUserRequest: SignInUserRequest,
        httpServletResponse: HttpServletResponse,
    ): UserResponse {
        return userService.get(request = signInUserRequest).let {
            val imageByteArray = it.profile?.let { profile ->
                profileDirectStorageService is ProfileDirectStorageService
                profileDirectStorageService.get(profile)
            }

            val uuid = randomUUID().toString()

            httpServletResponse.apply {
                addHeader(
                    "Authentication",
                    tokenProvider.createToken(user = it, uuid = uuid),
                )
                addCookie(
                    Cookie("identifyCode", uuid).apply { isHttpOnly = true },
                )
            }

            UserResponse(user = it, profile = imageByteArray)
        }
    }

    @PostMapping("signUp", consumes = [MULTIPART_FORM_DATA_VALUE])
    @ResponseStatus(CREATED)
    @Tag(name = "회원가입 API", description = "헤더로 Authentication, refreshToken 과 사용자 정보를 전달")
    fun signUp(
        @RequestPart request: SignUpUserRequest,
        @RequestPart profile: MultipartFile?,
        httpServletResponse: HttpServletResponse,
    ): UserResponse {
        profileDirectStorageService is ProfileDirectStorageService

        val fileName = profile?.let { profileDirectStorageService.save(multipartFile = profile) }
        val imageByteArray = fileName?.let { profileDirectStorageService.get(fileName = fileName) }

        return userService.save(request = request, profile = fileName).let {
            httpServletResponse.apply {
                val uuid = randomUUID().toString()

                addHeader(
                    "Authentication",
                    tokenProvider.createToken(user = it, uuid = uuid),
                )
                addCookie(
                    Cookie("identifyCode", uuid).apply { isHttpOnly = true },
                )
            }

            UserResponse(user = it, profile = imageByteArray)
        }
    }

    @PutMapping("{id}", consumes = [MULTIPART_FORM_DATA_VALUE])
    @ResponseStatus(NO_CONTENT)
    @Tag(name = "회원정보수정 API", description = "사용자의 정보 수정")
    fun update(
        @PathVariable("id") id: Long,
        @RequestPart request: UpdateUserRequest,
        @RequestPart(required = false) profile: MultipartFile?,
    ) {
        val fileName = profile?.let {
            profileDirectStorageService is ProfileDirectStorageService

            userService.get(id = id).profile?.apply {
                profileDirectStorageService.delete(fileName = this)
            }

            profileDirectStorageService.save(multipartFile = it)
        }

        userService.update(id = id, request = request, profile = fileName)
    }
}
