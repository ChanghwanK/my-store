package kr.my.store.auth

import org.springframework.stereotype.Component

@Component
class UserQueryAdapter(
    private val userRepository: UserQueryRepository
) {
    fun getByEmail(value: String) = userRepository.getByEmail(value)

    fun isExistsEmail(value: String): Boolean = userRepository.findByEmail(value) != null
}