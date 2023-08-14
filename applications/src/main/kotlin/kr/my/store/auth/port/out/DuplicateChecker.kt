package kr.my.store.auth.port.out

interface DuplicateChecker<T> {
    fun check(data: T)
}