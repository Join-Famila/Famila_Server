package join.famila.club.controller

import join.famila.club.request.CreateClubRequest
import join.famila.club.response.ClubResponse
import join.famila.club.service.ClubService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/club")
class ClubController(
    private val clubService: ClubService,
) {
    @GetMapping
    fun getClub() = clubService.getClub().map(::ClubResponse)

    @GetMapping("{id}")
    fun getClubById(@PathVariable id: Long) = clubService.getClubById(id).let(::ClubResponse)

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: CreateClubRequest) = clubService.create(request).let(::ClubResponse)
}
