package co.kr.searchvoca.domain.model

import co.kr.searchvoca.shared.domain.Level
import java.io.Serializable

data class Word(
    val id: Int?,
    val vocabularyId: Int,
    val word: String,
    val definition: String,
    var level: Level,
) : Serializable