package co.kr.searchvoca.data.model

import co.kr.searchvoca.domain.model.Vocabulary

data class VocabularyResult(
    val name: String,
    val id: Int? = null,
    val explanation: String? = null
)

fun VocabularyResult.toDomain() =
    Vocabulary(
        id,
        name,
        explanation
    )

fun Vocabulary.toModel() =
    VocabularyResult(
        name,
        id,
        explanation
    )