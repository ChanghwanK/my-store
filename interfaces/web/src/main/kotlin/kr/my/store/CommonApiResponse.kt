package kr.my.store

import kr.my.store.exception.BaseException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val DEFAULT_SUCCESS_MESSAGE = "SUCCESS"
const val DEFAULT_ERROR_MESSAGE = "FAIL";

data class ErrorSpec(
    val timestamp: String,
    val status: Int,
    val error: String,
    val message: String,
) {
    companion object {
        fun of(status: Int, ex: BaseException): ErrorSpec {
            val now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
            val error: String = ex::class.simpleName.toString()
            val message = ex.errorMsg
            return ErrorSpec(now, status, error, message)
        }

        fun of(status: Int, ex: MethodArgumentNotValidException): ErrorSpec {
            val now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
            val error: String = ex::class.simpleName.toString()
            val message = ex.fieldError?.defaultMessage ?: "Invalid Param"
            return ErrorSpec(now, status, error, message)
        }
    }
}

data class CommonApiResponse<T> (
    val message: String? = "",
    val data: T? = null,
) {
    companion object {
        fun <T> success(data: T?) = CommonApiResponse(message = DEFAULT_SUCCESS_MESSAGE, data = data)

        fun error(status: HttpStatus, ex: BaseException) = CommonApiResponse(message = DEFAULT_ERROR_MESSAGE, data = ErrorSpec.of(status.value(), ex))

        fun error(status: HttpStatus, ex: MethodArgumentNotValidException) = CommonApiResponse(message = DEFAULT_ERROR_MESSAGE, data = ErrorSpec.of(status.value(), ex))
    }
}

