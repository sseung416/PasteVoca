package co.kr.searchvoca.domain.usecase.search

import co.kr.searchvoca.domain.repository.SearchRepository
import kotlinx.coroutines.flow.flow
import co.kr.searchvoca.domain.model.UiState
import co.kr.searchvoca.shared.domain.Translate
import kotlinx.coroutines.flow.catch

class DetectLanguageUseCase(private val repo: SearchRepository) {
    operator fun invoke(word: String) = flow<UiState<Translate>> {
        val translate = repo.detectLanguage(word)
        emit(UiState.Success(translate))
    }.catch {
        emit(UiState.Failure(it))
    }
}