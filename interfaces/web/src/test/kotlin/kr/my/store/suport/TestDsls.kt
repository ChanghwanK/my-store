package kr.my.store.suport

import com.fasterxml.jackson.databind.ObjectMapper
import kr.my.store.CommonApiResponse
import kr.my.store.ErrorSpec
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockHttpServletRequestDsl
import org.springframework.test.web.servlet.result.ContentResultMatchersDsl

private val objectMapper = ObjectMapper()

internal fun ContentResultMatchersDsl.success(res: CommonApiResponse<Unit>) {
    return json(objectMapper.writeValueAsString(res), true)
}

internal fun ContentResultMatchersDsl.error(res: CommonApiResponse<ErrorSpec>) {
    return json(objectMapper.writeValueAsString(res), false)
}

internal fun MockHttpServletRequestDsl.jsonValue(value: Any) {
    content = objectMapper.writeValueAsString(value)
    contentType = MediaType.APPLICATION_JSON
}
