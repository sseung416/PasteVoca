package co.kr.searchvoca.domain.usecase.history

import co.kr.searchvoca.domain.model.UiState
import co.kr.searchvoca.domain.repository.WordRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetSearchHistoryCountUseCase(private val repo: WordRepository) {
    operator fun invoke() = flow<UiState<Int>> {
        val count = repo.getSearchHistoryCount()
        emit(UiState.Success(count))
    }.catch {
        emit(UiState.Failure(it))
    }
}