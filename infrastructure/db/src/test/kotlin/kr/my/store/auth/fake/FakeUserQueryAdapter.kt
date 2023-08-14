package kr.my.store.auth.fake

import kr.my.store.auth.UserJpaEntity
import kr.my.store.auth.UserQueryRepository
import java.util.*

class FakeUserQueryRepository: UserQueryRepository {
    companion object {
        private val DEFAULT_JPA_ENTITY = UserJpaEntity(email = "Test@mail", password = "test-1234")
    }
    private val USER_MEMORY_DB: MutableList<UserJpaEntity> = mutableListOf<UserJpaEntity>().apply {
        this.add(DEFAULT_JPA_ENTITY)
    }

    override fun findById(id: Long): Optional<UserJpaEntity> {
        return Optional.ofNullable(USER_MEMORY_DB[id.toInt()])
    }

    override fun findByEmail(email: String): UserJpaEntity? {
        return DEFAULT_JPA_ENTITY
    }

}
