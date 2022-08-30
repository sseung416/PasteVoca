package co.kr.searchvoca.remote

import co.kr.searchvoca.domain.model.ErrorEntity
import co.kr.searchvoca.domain.model.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection


class ApiErrorHandlerImpl : ErrorHandler {

    override fun getError(throwable: Throwable): ErrorEntity =
        when (throwable) {
            is IOException -> ErrorEntity.ApiError.Network

            is HttpException -> {
                when (throwable.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.ApiError.NotFound

                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.ApiError.AccessDenied

                    else -> ErrorEntity.ApiError.Unknown
                }
            }

            else -> ErrorEntity.ApiError.Unknown
        }
}