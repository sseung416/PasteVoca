package co.kr.searchvoca.domain.model

data class Word(
    val id: Int?,
    val vocabularyId: Int,
    val word: String,
    val definition: String,
    val level: Level,
)