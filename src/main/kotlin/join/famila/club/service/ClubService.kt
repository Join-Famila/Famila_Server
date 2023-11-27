package join.famila.club.service

import jakarta.transaction.Transactional
import join.famila.club.infrastructure.Club
import join.famila.club.infrastructure.ClubRepository
import join.famila.club.infrastructure.Member
import join.famila.club.infrastructure.MemberRepository
import join.famila.club.infrastructure.Role.LEADER
import join.famila.club.request.CreateClubRequest
import org.springframework.stereotype.Service

@Service
class ClubService(
    private val clubRepository: ClubRepository,

    private val memberRepository: MemberRepository,
) {
    @Transactional
    fun create(request: CreateClubRequest) {
        val club = Club(
            name = request.name,
            introduce = request.introduce,
            categories = request.categories,
        )

        val member = Member(
            club = club,
            userId = request.userId,
            role = LEADER,
        )

        clubRepository.save(club)
        memberRepository.save(member)
    }
}
