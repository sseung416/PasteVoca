package co.kr.searchvoca.remote.response

import co.kr.searchvoca.data.model.SearchResult

data class TranslateResponse(
    val data: TranslateData
)

data class TranslateData(
    val translations: List<Translations>
)

data class Translations(
    val translatedText: String
)

fun TranslateResponse.toModel() =
    data.translations.map {
        SearchResult(it.translatedText)
    }