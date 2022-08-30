package co.kr.searchvoca.domain.usecase.history

import co.kr.searchvoca.domain.model.ErrorHandler
import co.kr.searchvoca.domain.model.Result
import co.kr.searchvoca.domain.model.Word
import co.kr.searchvoca.domain.repository.WordRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoadSearchHistoryUseCase(
    private val repo: WordRepository,
    private val handler: ErrorHandler
) {

    operator fun invoke() = flow<Result<List<Word>>> {
        val words = repo.loadSearchHistory()
        emit(Result.Success(words))
    }.catch {
        emit(Result.Failure(handler.getError(it)))
    }
}