package kr.my.store.suport.auth

import kr.my.store.auth.SignUpReqDto

const val DEFAULT_EMAIL = "test@xxxmail.com"
const val DEFAULT_PW = "test-1234"

fun createSignUpReq(email: String = DEFAULT_EMAIL, password: String = DEFAULT_PW): SignUpReqDto {
    return SignUpReqDto(email, password)
}
