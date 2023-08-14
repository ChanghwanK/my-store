package kr.my.store.auth.port.`in`

interface SignUpUseCase {
    fun command(command: SignUpCommand)

    data class SignUpCommand(
        val email: String,
        val password: String,
    )
}