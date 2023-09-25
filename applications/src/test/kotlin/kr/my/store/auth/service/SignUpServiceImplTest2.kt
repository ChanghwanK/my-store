package kr.my.store.auth.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.mockk.*
import kr.my.store.auth.Email
import kr.my.store.auth.Password
import kr.my.store.auth.User
import kr.my.store.auth.port.`in`.SignUpUseCase
import kr.my.store.auth.port.out.DuplicateChecker
import kr.my.store.auth.port.out.UserRegisterPort
import kr.my.store.fake.DEFAULT_EMAIL_VALUE
import kr.my.store.fake.FakeEmailDuplicateChecker
import kr.my.store.fake.FakeUserRegister
import kr.my.store.fake.NOT_DUPLICATED_EMAIL_VALUE

class SignUpServiceImplTest2 : BehaviorSpec({
    val userRegisterPort = FakeUserRegister()
    val userEmailDuplicateChecker = FakeEmailDuplicateChecker()

    val sut = SignUpServiceImpl(userEmailDuplicateChecker, userRegisterPort)

    Given("회원가입 Command가 온다면") {
        val email = NOT_DUPLICATED_EMAIL_VALUE
        val password = "test-1234"

        When("중복된 Email이 아닌 경우") {
            then("Regist Port를 통해 영속화된다.") {
                sut.command(SignUpUseCase.SignUpCommand(email, password))
                userRegisterPort.index shouldBe FakeUserRegister.START_INDEX + 1
            }
        }

        When("중복된 Email인 경우") {
            then("예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    sut.command(SignUpUseCase.SignUpCommand(DEFAULT_EMAIL_VALUE, password))
                }
            }
        }
    }
})
