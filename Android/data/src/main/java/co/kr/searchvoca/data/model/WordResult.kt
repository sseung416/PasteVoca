package co.kr.searchvoca.data.model

import co.kr.searchvoca.domain.model.Level
import co.kr.searchvoca.domain.model.Word

data class WordResult(
    val vocabularyId: Int,
    val word: String,
    val definition: String,
    var level: Int = Level.EASY.ordinal,
    var id: Int? = null
)

fun WordResult.toDomain() =
    Word(
        id ?: 0,
        vocabularyId,
        word,
        definition,
        Level.values()[level]
    )

fun Word.toModel() =
    WordResult(
        vocabularyId,
        word,
        definition,
        level.ordinal,
        id
    )