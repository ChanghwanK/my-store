package kr.my.store.auth

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.*
import kr.my.store.CommonApiResponse
import kr.my.store.auth.port.`in`.SignUpUseCase
import kr.my.store.suport.RESTAPIUnitTest
import kr.my.store.suport.auth.createSignUpReq
import kr.my.store.suport.jsonValue
import org.junit.jupiter.api.DisplayName
import kr.my.store.suport.success
import org.hamcrest.Matchers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter

@DisplayName("[UNIT} AuthApiController")
@RESTAPIUnitTest(controllers = [AuthApiController::class])
class AuthApiControllerTest(
    private val webApplicationContext: WebApplicationContext,
    @MockkBean
    val signUpUseCase: SignUpUseCase,
) : DescribeSpec({
    isolationMode = IsolationMode.SingleInstance

    val AUTH_API_URI = "/api/v1/auth"
    val mockMvc: MockMvc =  MockMvcBuilders
        .webAppContextSetup(webApplicationContext)
        .addFilter<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8"))
        .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
        .build()

    beforeEach {
        clearMocks(signUpUseCase)
    }

    describe("회원가입 API") {
        context("유효한 정보 회원가입 요청을 보냈을 경우") {
            val SIGN_UP_REQ_FIXTURE = createSignUpReq()
            it("응답코드 200을 반환하고 data는 빈 값") {

                every { signUpUseCase.command(any()) } just Runs

                val SUCCESS_RESPONSE_FIXTURE = CommonApiResponse.success(Unit)
                mockMvc.post(AUTH_API_URI) {
                    jsonValue(SIGN_UP_REQ_FIXTURE)
                }.andExpect {
                    status { isOk() }
                    content {
                        success(SUCCESS_RESPONSE_FIXTURE)
                    }
                }

                verify(exactly = 1) { signUpUseCase.command(any()) }
            }
        }

        context("회원가입 요청에 공백이 포함된 경우") {
            val EMAIL_EMPTY_FIXTURE = createSignUpReq(email = "")
            val PASSWORD_EMPTY_FIXTURE = createSignUpReq(password = "")

            it("[Email 공백]") {
                mockMvc.post(AUTH_API_URI) {
                    jsonValue(EMAIL_EMPTY_FIXTURE)
                }.andExpect {
                    status { isBadRequest() }
                }

                verify(exactly = 0) { signUpUseCase.command(any()) }
            }

            it("Password 공백") {
                mockMvc.post(AUTH_API_URI) {
                    jsonValue(PASSWORD_EMPTY_FIXTURE)
                }.andExpect {
                    status { isBadRequest() }
                }

                verify(exactly = 0) { signUpUseCase.command(any()) }
            }
        }
    }
})

