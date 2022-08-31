package co.kr.searchvoca.domain.model

sealed class ErrorEntity {

    sealed class ApiError : ErrorEntity() {

        object Network : ApiError()

        object NotFound : ApiError()

        object AccessDenied : ApiError()

        object Unknown : ApiError()
    }

    sealed class LocalError : ErrorEntity()
}

