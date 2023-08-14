package kr.my.store.auth

import kr.my.store.CommonApiResponse
import kr.my.store.auth.port.`in`.SignUpUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/auth")
class AuthApiController(
    private val signUpUseCase: SignUpUseCase
) {
    @PostMapping("")
    fun signUp(@RequestBody @Valid dto: SignUpReqDto): CommonApiResponse<Unit> {
        signUpUseCase.command(SignUpUseCase.SignUpCommand(email = dto.email, password = dto.password))
        return CommonApiResponse.success(Unit)
    }
}