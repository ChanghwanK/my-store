package kr.my.store.fake

import kr.my.store.auth.User
import kr.my.store.auth.port.out.UserRegisterPort

internal class FakeUserRegister: UserRegisterPort {
    private var _index: Long = START_INDEX
    private val _memoryStore = mutableMapOf<Long, User>()

    val index: Long
        get() = _index

    companion object {
        const val START_INDEX = 1L
    }

    override fun register(user: User) {
        _memoryStore[_index] = user
        _index++
    }
}