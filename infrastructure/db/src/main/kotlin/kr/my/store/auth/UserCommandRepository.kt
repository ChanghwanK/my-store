package kr.my.store.auth

import org.springframework.data.repository.Repository

interface UserCommandRepository: Repository<UserJpaEntity, Long> {
    fun save(userJpaEntity: UserJpaEntity)
}