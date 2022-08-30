package co.kr.searchvoca.domain.model

data class Vocabulary(
    val id: Int? = null,
    val name: String,
    val explanation: String? = null
//    val language: String,
//    val isCorrections: Boolean = false,
//    val date: Long? = null
)

// todo 다른 방법
val default = Vocabulary(name = "미정")