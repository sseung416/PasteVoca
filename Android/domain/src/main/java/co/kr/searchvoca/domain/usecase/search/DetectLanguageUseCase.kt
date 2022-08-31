package co.kr.searchvoca.domain.usecase.search

import co.kr.searchvoca.domain.model.ErrorHandler
import co.kr.searchvoca.domain.repository.SearchRepository
import kotlinx.coroutines.flow.flow
import co.kr.searchvoca.domain.model.Result
import co.kr.searchvoca.shared.domain.Translate
import kotlinx.coroutines.flow.catch

class DetectLanguageUseCase(
    private val repo: SearchRepository,
    private val handler: ErrorHandler
) {

    operator fun invoke(word: String) = flow<Result<Translate>> {
        // todo null check
        val translate = repo.detectLanguage(word)
        emit(Result.Success(translate!!))
    }.catch {
        emit(Result.Failure(handler.getError(it)))
    }
}