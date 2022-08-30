package co.kr.searchvoca.domain.usecase.vocabulary

import co.kr.searchvoca.domain.model.ErrorHandler
import co.kr.searchvoca.domain.model.Result
import co.kr.searchvoca.domain.model.Vocabulary
import co.kr.searchvoca.domain.repository.VocabularyRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoadVocabulariesUseCase(
    private val repo: VocabularyRepository,
    private val handler: ErrorHandler
) {

    operator fun invoke() = flow<Result<List<Vocabulary>>> {
        val vocabularies = repo.loadVocabularies()
        emit(Result.Success(vocabularies))
    }.catch {
        emit(Result.Failure(handler.getError(it)))
    }

    operator fun invoke(id: Int) = flow<Result<Vocabulary>> {
        val vocabulary = repo.loadVocabularyById(id)
        emit(Result.Success(vocabulary))
    }.catch {
        emit(Result.Failure(handler.getError(it)))
    }
}