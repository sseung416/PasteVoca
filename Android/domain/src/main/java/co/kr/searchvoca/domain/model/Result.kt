package co.kr.searchvoca.domain.model

sealed class Result<out T> {

    data class Success<T>(val data: T) : Result<T>()

    data class Failure(val error: ErrorEntity) : Result<Nothing>()

    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Failure -> "Failure[error=$error]"
            Loading -> "Loading"
        }
    }
}

fun <T> Result<T>.successOr(fallback: T) =
    (this as? Result.Success<T>)?.data ?: fallback

fun <T> Result<T>.successOrNull() =
    (this as? Result.Success<T>)?.data

fun <T> Result<T>?.throwableOrNull() =
    (this as? Result.Failure)?.error