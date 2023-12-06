package join.famila.user.service

import join.famila.user.controller.data.SignInRequest
import join.famila.user.controller.data.SignUpRequest
import join.famila.user.infrastructure.Identifier
import join.famila.user.infrastructure.User
import join.famila.user.infrastructure.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun get(id: Long) = userRepository.findByIdOrNull(id = id) ?: throw NoSuchElementException()

    fun get(request: SignInRequest): User {
        return userRepository.findByIdentifier(
            Identifier(
                uid = request.uid,
                provider = request.provider,
            )
        ) ?: throw NoSuchElementException()
    }

    fun save(request: SignUpRequest): User {
        return userRepository.save(User.of(request = request))
    }
}
