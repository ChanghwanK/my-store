package kr.my.store.annotations

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
@Transactional(readOnly = true)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class QueryAdapter()
