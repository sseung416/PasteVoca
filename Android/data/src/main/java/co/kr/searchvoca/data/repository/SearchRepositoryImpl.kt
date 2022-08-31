package co.kr.searchvoca.data.repository

import co.kr.searchvoca.data.datasouce.SearchDataSource
import co.kr.searchvoca.data.model.SearchResult
import co.kr.searchvoca.data.model.toDomain
import co.kr.searchvoca.domain.model.Definition
import co.kr.searchvoca.domain.repository.SearchRepository
import co.kr.searchvoca.shared.domain.Translate

class SearchRepositoryImpl(
    private val dataSource: SearchDataSource
) : SearchRepository {

    override suspend fun detectLanguage(word: String): Translate? =
        dataSource.detectLanguage(word)

    override suspend fun loadDictionarySearchResults(word: String): List<Definition> =
        dataSource.loadDictionarySearchResults(word).map(SearchResult::toDomain)

    override suspend fun translateWord(word: String, tr: Translate): List<Definition> =
        dataSource.translateWord(word, tr).map(SearchResult::toDomain)
}