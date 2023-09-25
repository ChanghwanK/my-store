package kr.my.store.auth

import kr.my.store.auth.port.out.DuplicateChecker
import org.springframework.stereotype.Component

@Component
class EmailDuplicateChecker(
    private val userQueryAdapter: UserQueryAdapter
) : DuplicateChecker<Email> {
    override fun check(data: Email) {
        if (userQueryAdapter.isExistsEmail(data.value))
            throw IllegalArgumentException("중복된 이메일 입니다.")
    }
}