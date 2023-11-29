package join.famila.club.service

import jakarta.transaction.Transactional
import join.famila.club.infrastructure.Club
import join.famila.club.infrastructure.ClubRepository
import join.famila.club.infrastructure.Member
import join.famila.club.infrastructure.MemberRepository
import join.famila.club.infrastructure.Role.LEADER
import join.famila.club.request.CreateClubRequest
import join.famila.core.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ClubService(
    private val clubRepository: ClubRepository,

    private val memberRepository: MemberRepository,
) {
    fun getClub(): List<Club> = clubRepository.findAll()

    fun getClubById(id: Long) = clubRepository.findByIdOrNull(id) ?: throw NotFoundException("해당 Id를 가진 Club이 없습니다.")

    @Transactional
    fun create(request: CreateClubRequest): Club {
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

        return clubRepository.save(club).also {
            memberRepository.save(member)
        }
    }
}
