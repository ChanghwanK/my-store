package kr.my.store.auth

data class Email(val value: String)
data class Password(val value: String)

data class User(
    val email: Email,
    val password: Password,
)