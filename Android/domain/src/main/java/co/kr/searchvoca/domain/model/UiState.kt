package co.kr.searchvoca.domain.model

sealed class UiState<out T>{
    data class Success<T>(val data: T) : UiState<T>()

    data class Failure(val throwable: Throwable) : UiState<Nothing>()

    object Loading : UiState<Nothing>()
}

fun <T> UiState<T>.successOr(fallback: T) =
    (this as? UiState.Success<T>)?.data ?: fallback

fun <T> UiState<T>.successOrNull() =
    (this as? UiState.Success<T>)?.data

fun <T> UiState<T>.throwableOrNull() =
    (this as? UiState.Failure)?.throwable