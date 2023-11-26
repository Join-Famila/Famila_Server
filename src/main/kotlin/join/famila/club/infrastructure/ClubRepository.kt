package join.famila.club.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClubRepository : JpaRepository<Club, Long>
