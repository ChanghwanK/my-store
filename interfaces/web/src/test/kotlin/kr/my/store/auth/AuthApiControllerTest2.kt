package kr.my.store.auth

import com.ninjasquad.springmockk.MockkBean
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.verify
import kr.my.store.BaseTestController
import kr.my.store.auth.port.`in`.SignUpUseCase
import kr.my.store.suport.RESTAPIUnitTest
import kr.my.store.suport.auth.createSignUpReq
import kr.my.store.suport.jsonValue
import kr.my.store.suport.success
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

private const val AUTH_API_URI = "/api/v1/auth"

@DisplayName("[UNIT] AuthApiController")
@RESTAPIUnitTest(controllers = [AuthApiController::class])
class AuthApiControllerTest2 : BaseTestController() {

    @MockkBean
    private lateinit var singUpUseCase: SignUpUseCase

    @DisplayName("회원가입 API는")
    @Nested
    inner class Describe {

        @DisplayName("유효한 데이터로 요청을 받는다면")
        @Nested
        inner class Context {

            @Test
            fun `200을 응답한다`() {
                val SIGN_UP_REQ_FIXTURE = createSignUpReq()
                every { singUpUseCase.command(any()) } just Runs

                mockMvc.post(AUTH_API_URI) {
                    jsonValue(SIGN_UP_REQ_FIXTURE)
                }.andExpect {
                    status { isOk() }
                }

                verify(exactly = 1) { singUpUseCase.command(any())}
            }
        }

        @DisplayName("요청의 패스워드가 공백이라면")
        @Nested
        inner class InvalidDataContext {
            @Test
            fun `400을 리턴한다`() {
                val SIGN_UP_REQ_FIXTURE = createSignUpReq(password = "")

                mockMvc.post(AUTH_API_URI) {
                    jsonValue(SIGN_UP_REQ_FIXTURE)
                }.andExpect {
                    status { isBadRequest() }
                }

                verify(exactly = 0) { singUpUseCase.command(any())}
            }
        }

        @DisplayName("요청의 Email이 공백이라면")
        @Nested
        inner class Context_Of_Invalid_Email {
            @Test
            fun `400을 리턴한다`() {
                val SIGN_UP_REQ_FIXTURE = createSignUpReq(email = "")

                mockMvc.post(AUTH_API_URI) {
                    jsonValue(SIGN_UP_REQ_FIXTURE)
                }.andExpect {
                    status { isBadRequest() }
                }

                verify(exactly = 0) { singUpUseCase.command(any())}
            }
        }
    }
}
