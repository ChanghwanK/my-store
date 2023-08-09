package kr.my.store

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyStoreWebApplication

fun main(args: Array<String>) {
    runApplication<MyStoreWebApplication>(*args)
}