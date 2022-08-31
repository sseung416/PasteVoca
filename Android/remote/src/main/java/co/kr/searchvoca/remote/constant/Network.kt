package co.kr.searchvoca.remote.constant

internal object Network {
    const val DICTIONARY_BASE_URL = "https://stdict.korean.go.kr/api/"
    const val DICTIONARY_GET_SEARCH_RESULT = "search.do"

    const val TRANSLATE_BASE_URL = "https://translation.googleapis.com/"
    const val TRANSLATE_POST_TRANSLATE = "language/translate/v2"
    const val TRANSLATE_POST_DETECTIVE = "language/translate/v2/detect"
}