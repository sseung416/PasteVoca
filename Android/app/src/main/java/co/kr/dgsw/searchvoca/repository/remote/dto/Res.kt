package co.kr.dgsw.searchvoca.repository.remote.dto

data class Res<T>(
    val res: T?,
    val message: String,
    val status: Int
)