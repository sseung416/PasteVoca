package co.kr.searchvoca.domain.usecase.search

import co.kr.searchvoca.domain.repository.SearchRepository
import co.kr.searchvoca.shared.domain.Translate

@Deprecated("")
class TranslateWordUseCase(private val repo: SearchRepository) {
    suspend operator fun invoke(
        word: String,
        langCode: Translate
    ) {
        repo.translateWord(
            word,
            langCode
        )
    }
}