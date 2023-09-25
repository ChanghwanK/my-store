package kr.my.store.auth

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import java.util.*

class EmailDuplicateCheckerTest : BehaviorSpec({
//    val fakeQueryRepository = FakeUserQueryRepository()
//    val fakeQueryAdapter = UserQueryAdapter(fakeQueryRepository)
//    val sut = EmailDuplicateChecker(fakeQueryAdapter)

    Given("중복된 Email로") {
        val fakeQueryAdapter = UserQueryAdapter(object: UserQueryRepository {
            override fun findById(id: Long): Optional<UserJpaEntity> {
                return Optional.ofNullable(UserJpaEntity(email = "tew", password = "321"))
            }

            override fun findByEmail(email: String): UserJpaEntity? {
                throw IllegalArgumentException()
            }

        })

        val sut = EmailDuplicateChecker(fakeQueryAdapter)

        When("Email 중복 체크를 하면") {
            Then("이메일 중복 예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    sut.check(Email(value = "test@mail.com"))
                }
            }
        }
    }
})
