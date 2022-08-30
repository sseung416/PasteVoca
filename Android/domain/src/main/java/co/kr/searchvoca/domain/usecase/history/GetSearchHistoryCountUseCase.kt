package co.kr.searchvoca.domain.usecase.history

import co.kr.searchvoca.domain.model.ErrorHandler
import co.kr.searchvoca.domain.model.Result
import co.kr.searchvoca.domain.repository.WordRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetSearchHistoryCountUseCase(
    private val repo: WordRepository,
    private val handler: ErrorHandler
) {

    operator fun invoke() = flow<Result<Int>> {
        emit(Result.Loading)
        val count = repo.getSearchHistoryCount()
        emit(Result.Success(count))
    }.catch {
        emit(Result.Failure(handler.getError(it)))
    }
}