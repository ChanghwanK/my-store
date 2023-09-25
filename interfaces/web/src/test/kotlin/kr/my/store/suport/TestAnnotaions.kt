package kr.my.store.suport

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import java.lang.annotation.Inherited
import kotlin.reflect.KClass

@Inherited
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ExtendWith(MockKExtension::class)
@WebMvcTest
annotation class RESTAPIUnitTest(
    val controllers: Array<KClass<*>> = []
)