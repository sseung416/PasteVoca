package co.kr.searchvoca.remote.util

import retrofit2.Response
import kotlin.Exception

internal fun <T> Response<T>.requireSuccessfulBody(requestName: String): T {
    return if (isSuccessful) {
        body()!!
    } else {
        // todo exception 세분화 및 종류 구분
        throw Exception("Request $requestName is fail. ${code()}: ${message()}")
    }
}