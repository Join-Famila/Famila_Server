package join.famila.club.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import join.famila.club.infrastructure.Category
import join.famila.club.infrastructure.ClubRepository
import join.famila.club.infrastructure.MemberRepository
import join.famila.club.infrastructure.Tag.DANCING
import join.famila.club.infrastructure.Tag.HEALTH
import join.famila.club.request.CreateClubRequest
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ClubServiceTest(
    private val clubRepository: ClubRepository,

    private val memberRepository: MemberRepository,
) : BehaviorSpec({
    Given("모임을 만들기 위한 정보를 입력하고") {
        val request = CreateClubRequest(
            userId = 1,
            name = "실버 스윙 댄스 모임",
            introduce = "스윙 댄스 재밌게 할 모임원들 구합니다",
            categories = listOf(Category(HEALTH), Category(DANCING)),
        )

        val clubService = ClubService(
            clubRepository = clubRepository,
            memberRepository = memberRepository,
        )

        When("모임을 생성한다면") {
            val club = clubService.create(request)

            Then("정상적으로 모임이 생성된다.") {
                val clubs = clubRepository.findAll()

                clubs.size shouldBe 1
                clubs[0].members[0].userId shouldBe 1
            }
        }
    }
})
