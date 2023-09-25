package kr.my.store.auth.port.out

import kr.my.store.auth.User

interface UserRegisterPort {
    fun register(user: User)
}
