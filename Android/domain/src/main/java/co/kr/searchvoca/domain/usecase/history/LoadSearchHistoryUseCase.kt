package co.kr.searchvoca.domain.usecase.history

import co.kr.searchvoca.domain.model.UiState
import co.kr.searchvoca.domain.model.Word
import co.kr.searchvoca.domain.repository.WordRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoadSearchHistoryUseCase(private val repo: WordRepository) {
    operator fun invoke() = flow<UiState<List<Word>>> {
        val words = repo.loadSearchHistory()
        emit(UiState.Success(words))
    }.catch {
        emit(UiState.Failure(it))
    }
}