package co.kr.searchvoca.domain.model

interface ErrorHandler {

    fun getError(throwable: Throwable): ErrorEntity
}

