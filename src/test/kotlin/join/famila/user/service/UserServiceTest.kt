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
import join.famila.user.controller.data.SignInUserRequest
import join.famila.user.controller.data.SignUpUserRequest
import join.famila.user.controller.data.UpdateUserRequest
import join.famila.user.infrastructure.Gender.MALE
import join.famila.user.infrastructure.UserRepository
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockMultipartFile
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class UserServiceTest(
    private val userRepository: UserRepository,
) : BehaviorSpec({
    val userService = UserService(userRepository = userRepository)

    val uid = UUID.randomUUID().toString()

    Given("회원 정보를 입력하고") {
        val request = SignUpUserRequest(
            uid = uid,
            provider = "kakao",
            name = "홍길동",
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

        val profile = MockMultipartFile("testName", "testContent".toByteArray())

        When("회원가입을 한다면") {
            val user = userService.save(request = request, profile = profile)

            Then("정상적으로 회원가입이 된다") {
                user.name shouldBe "홍길동"
            }
        }
    }

    Given("로그인 정보를 입력하고") {
        val request = SignInUserRequest(
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

    Given("회원 수정 정보를 입력하고") {
        val signUpUserRequest = SignUpUserRequest(
            uid = uid,
            provider = "kakao",
            name = "홍길동",
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

        val signUpProfile = MockMultipartFile("testName", "testContent".toByteArray())

        val id = userService.save(signUpUserRequest, signUpProfile).id

        val request = UpdateUserRequest(
            phoneNumber = "01009876543",
            location = LocationRequest(
                address = "서울특별시 금천구 독산동",
                latitude = BigDecimal.valueOf(123.4),
                longitude = BigDecimal.valueOf(233.624),
            ),
            introduce = "수정한 자기소개입니다.",
            categories = setOf(
                Category(tag = GOLF),
                Category(tag = MEDITATION),
            ),
        )

        val profile = MockMultipartFile("testName", "testContent".toByteArray())

        When("수정 요청을하면") {
            val user = userService.update(id = id, request = request, profile = profile)

            Then("수정 된다") {
                user.phoneNumber shouldBe "01009876543"
                user.location.address shouldBe "서울특별시 금천구 독산동"
                user.location.latitude shouldBe BigDecimal.valueOf(123.4)
                user.location.longitude shouldBe BigDecimal.valueOf(233.624)
                user.introduce shouldBe "수정한 자기소개입니다."
            }
        }
    }
})
