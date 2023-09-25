package kr.my.store

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
abstract class BaseTestController {

    @Autowired
    lateinit var objectMapper: ObjectMapper
    lateinit var mockMvc: MockMvc

    @BeforeEach
    private fun setUp(webApplicationContext: WebApplicationContext) {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .addFilter<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8"))
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .build()
    }
}