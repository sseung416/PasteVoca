package co.kr.searchvoca.data.datasouce

import co.kr.searchvoca.data.model.SearchResult
import co.kr.searchvoca.shared.domain.Translate

interface SearchDataSource {
    suspend fun loadDictionarySearchResults(word: String): List<SearchResult>

    suspend fun translateWord(
        word: String,
        tr: Translate
    ): List<SearchResult>

    suspend fun detectLanguage(word: String): Translate?
}