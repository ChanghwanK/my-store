package kr.my.store.auth

import javax.validation.constraints.NotBlank

data class SignUpReqDto(
    @field:NotBlank(message = "Email은 공백이 될 수 없습니다.")
    val email: String,

    @field:NotBlank(message = "Password는 공백이 될 수 없습니다.")
    val password: String,
)
