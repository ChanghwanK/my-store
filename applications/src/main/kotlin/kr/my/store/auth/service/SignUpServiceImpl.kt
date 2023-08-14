package kr.my.store.auth.service

import kr.my.store.auth.Email
import kr.my.store.auth.Password
import kr.my.store.auth.User
import kr.my.store.auth.port.`in`.SignUpUseCase
import kr.my.store.auth.port.out.DuplicateChecker
import kr.my.store.auth.port.out.UserRegisterPort
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class SignUpServiceImpl(
    private val emailDuplicateChecker: DuplicateChecker<Email>,
    private val userRegisterPort: UserRegisterPort,
) : SignUpUseCase {

    override fun command(command: SignUpUseCase.SignUpCommand) {
        val email = Email(command.email)
        println("Duplicated Check")
        emailDuplicateChecker.check(email)

        userRegisterPort.register(User(email = email, password = Password(command.password)))
    }
}