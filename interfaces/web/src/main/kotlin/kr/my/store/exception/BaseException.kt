package kr.my.store.exception

abstract class BaseException(val errorMsg: String) : RuntimeException(errorMsg)