package join.famila.user.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID
import join.famila.club.infrastructure.Category
import join.famila.club.infrastructure.Tag.BIKING
import join.famila.club.infrastructure.Tag.GOLF
import join.famila.club.infrastructure.Tag.MEDITATION
import join.famila.user.controller.data.LocationRequest
import join.famila.user.controller.data.SignInRequest
import join.famila.user.controller.data.SignUpRequest
import join.famila.user.infrastructure.Gender.MALE
import join.famila.user.infrastructure.UserRepository
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest(
    private val userRepository: UserRepository,
) : BehaviorSpec({
    val userService = UserService(userRepository = userRepository)

    val uid = UUID.randomUUID().toString()

    Given("회원 정보를 입력하고") {
        val request = SignUpRequest(
            uid = uid,
            provider = "kakao",
            name = "홍길동",
            profile = "asldkvmlaksdmvlakdvmalkvaldvnaldkvmaldkmfqokwmqlkdmlsakdmalkdv",
            gender = MALE,
            phoneNumber = "01012341234",
            location = LocationRequest(
                address = "서울특별시 구로구 구로동",
                latitude = BigDecimal.valueOf(123.4),
                longitude = BigDecimal.valueOf(233.624)
            ),
            birthDay = LocalDate.of(1991, 1, 1),
            introduce = "안녕하세요 자기소개 입니다.",
            categories = setOf(
                Category(tag = BIKING),
                Category(tag = GOLF),
                Category(tag = MEDITATION),
            ),
        )

        When("회원가입을 한다면") {
            val user = userService.save(request = request)

            Then("정상적으로 회원가입이 된다") {
                user.name shouldBe "홍길동"
            }
        }
    }

    Given("로그인 정보를 입력하고") {
        val request = SignInRequest(
            uid = uid,
            provider = "kakao",
        )

        When("로그인을 요청한다면") {
            val user = userService.get(request)

            Then("일치하는 회원을 찾는다") {
                user shouldNotBe null
            }
        }
    }
})
