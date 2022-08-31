package co.kr.searchvoca.data.model

import co.kr.searchvoca.domain.model.Definition

data class SearchResult(
    val definition: String
)

fun SearchResult.toDomain() =
    Definition(definition)