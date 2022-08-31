package co.kr.searchvoca.domain.usecase.word

import co.kr.searchvoca.domain.model.ErrorHandler
import co.kr.searchvoca.domain.model.Result
import co.kr.searchvoca.domain.model.Word
import co.kr.searchvoca.domain.repository.WordRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class EditWordUseCase(
    private val repo: WordRepository,
    private val handler: ErrorHandler
) {

    operator fun invoke(word: Word) = flow<Result<Unit>> {
        val res = repo.editWord(word)
        emit(Result.Success(res))
    }.catch {
        emit(Result.Failure(handler.getError(it)))
    }

    operator fun invoke(ids: List<Int>, vocabularyId: Int) = flow<Result<Unit>> {
        val res = repo.changeWordVocabulary(ids, vocabularyId)
        emit(Result.Success(res))
    }.catch {
        emit(Result.Failure(handler.getError(it)))
    }
}