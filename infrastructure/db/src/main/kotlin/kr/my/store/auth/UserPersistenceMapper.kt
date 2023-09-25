package kr.my.store.auth

import org.springframework.stereotype.Component

@Component
class UserPersistenceMapper {
    fun mapToJpaEntity(user: User): UserJpaEntity {
        return UserJpaEntity(email = user.email.value, password = user.password.value)
    }
}
