package join.famila.user.service

import join.famila.user.controller.data.SignInUserRequest
import join.famila.user.controller.data.SignUpUserRequest
import join.famila.user.controller.data.UpdateUserRequest
import join.famila.user.infrastructure.Identifier
import join.famila.user.infrastructure.User
import join.famila.user.infrastructure.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun get(id: Long) = userRepository.findByIdOrNull(id = id) ?: throw NoSuchElementException()

    fun get(request: SignInUserRequest): User {
        return userRepository.findByIdentifier(
            Identifier(
                uid = request.uid,
                provider = request.provider,
            )
        ) ?: throw NoSuchElementException()
    }

    fun save(request: SignUpUserRequest): User {
        return userRepository.save(User.of(request = request))
    }

    @Transactional
    fun update(id: Long, request: UpdateUserRequest): User {
        return userRepository.findByIdOrNull(id = id)
            ?.apply { update(request = request) }
            ?: throw NoSuchElementException()
    }
}
