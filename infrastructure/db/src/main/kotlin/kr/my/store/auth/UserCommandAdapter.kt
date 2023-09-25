package kr.my.store.auth

import kr.my.store.annotations.CommandAdapter
import kr.my.store.auth.port.out.UserRegisterPort

@CommandAdapter
class UserCommandAdapter(
    private val userCommandRepository: UserCommandRepository,
    private val userPersistenceMapper: UserPersistenceMapper,
): UserRegisterPort {

    override fun register(user: User) {
        userCommandRepository.save(userPersistenceMapper.mapToJpaEntity(user))
    }
}