package kr.my.store.aop.exception

import kr.my.store.CommonApiResponse
import kr.my.store.ErrorSpec
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleBaseException(ex: MethodArgumentNotValidException): CommonApiResponse<ErrorSpec> {
        return CommonApiResponse.error(HttpStatus.BAD_REQUEST, ex)
    }
}