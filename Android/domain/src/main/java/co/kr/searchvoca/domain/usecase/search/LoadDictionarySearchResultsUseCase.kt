package co.kr.searchvoca.domain.usecase.search

import co.kr.searchvoca.domain.repository.SearchRepository

@Deprecated("")
class LoadDictionarySearchResultsUseCase(private val repo: SearchRepository) {
    suspend operator fun invoke(word: String) = kotlin.runCatching {
        repo.loadDictionarySearchResults(word)
    }
}