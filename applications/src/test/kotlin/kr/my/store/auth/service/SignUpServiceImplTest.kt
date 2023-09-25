package kr.my.store.auth.service

import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.*
import kr.my.store.auth.Email
import kr.my.store.auth.Password
import kr.my.store.auth.User
import kr.my.store.auth.port.`in`.SignUpUseCase
import kr.my.store.auth.port.out.DuplicateChecker
import kr.my.store.auth.port.out.UserRegisterPort

class SignUpServiceImplTest : BehaviorSpec({

    val userRegisterPort = mockk<UserRegisterPort>()
    val userEmailDuplicateChecker = mockk<DuplicateChecker<Email>>()

    val sut = SignUpServiceImpl(userEmailDuplicateChecker, userRegisterPort)

    Given("회원가입 Command가 온다면") {
        When("중복된 Email이 아닌 경우") {
            every { userEmailDuplicateChecker.check(any()) } just Runs
            every { userRegisterPort.register(any()) } just Runs

            sut.command(SignUpUseCase.SignUpCommand("test@email", "password"))
            then("Regist Port를 통해 영속화된다.") {
                verify(exactly = 1) { userRegisterPort.register(any()) }
            }
        }
    }
})
