package kr.my.store.auth

import org.springframework.data.repository.Repository
import java.util.Optional
import javax.persistence.EntityNotFoundException

//fun UserQueryRepository.getById(id: Long): UserJpaEntity = findByIdOrNull(id)
//    ?: throw EntityNotFoundException("User가 존재하지 않습니다.. id: $id")


fun UserQueryRepository.getById(id: Long) = findByIdOrNull(id) ?: EntityNotFoundException("잘못된 Id $id")

fun UserQueryRepository.findByIdOrNull(id: Long): UserJpaEntity? = findById(id).orElse(null)

fun UserQueryRepository.getByEmail(email: String): UserJpaEntity = findByEmail(email = email)
    ?: throw EntityNotFoundException("User가 존재하지 않습니다. Email: $email")


interface UserQueryRepository: Repository<UserJpaEntity, Long> {

    fun findById(id: Long): Optional<UserJpaEntity>
//    fun findByIdOrNull(id: Long): UserJpaEntity? = findById(id!!).orElse(null)
    fun findByEmail(email: String): UserJpaEntity?
}