package kr.my.store.auth.port.out

import kr.my.store.auth.Email

interface UserEmailReadPort {
    fun findByEmail(value: String): Email?
}