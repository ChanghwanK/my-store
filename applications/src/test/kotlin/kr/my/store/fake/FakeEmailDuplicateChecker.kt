package kr.my.store.fake

import kr.my.store.auth.Email
import kr.my.store.auth.port.out.DuplicateChecker

const val DEFAULT_EMAIL_VALUE = "test@mail.com"
const val NOT_DUPLICATED_EMAIL_VALUE = "test2@mail.com"

class FakeEmailDuplicateChecker: DuplicateChecker<Email> {
    private val _memoryStore: MutableMap<Email, Any> = mutableMapOf()

    init {
        _memoryStore[Email(DEFAULT_EMAIL_VALUE)] = Unit
    }

    override fun check(data: Email) {
        if (_memoryStore[data] != null)
            throw IllegalArgumentException()
    }
}