package co.kr.searchvoca.domain.model

data class Vocabulary(
    val id: Int?,
    val name: String,
    val explanation: String?
//    val language: String,
//    val isCorrections: Boolean = false,
//    val date: Long? = null
)