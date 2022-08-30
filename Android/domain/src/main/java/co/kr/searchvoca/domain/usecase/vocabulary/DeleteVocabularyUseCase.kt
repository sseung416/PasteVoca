package co.kr.searchvoca.domain.usecase.vocabulary

import co.kr.searchvoca.domain.model.ErrorHandler
import co.kr.searchvoca.domain.model.Result
import co.kr.searchvoca.domain.model.Vocabulary
import co.kr.searchvoca.domain.repository.VocabularyRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class DeleteVocabularyUseCase(
    private val repo: VocabularyRepository,
    private val handler: ErrorHandler
) {

    operator fun invoke(vocabulary: Vocabulary) = flow<Result<Unit>> {
        val res = repo.deleteVocabulary(vocabulary)
        emit(Result.Success(res))
    }.catch {
        emit(Result.Failure(handler.getError(it)))
    }
}