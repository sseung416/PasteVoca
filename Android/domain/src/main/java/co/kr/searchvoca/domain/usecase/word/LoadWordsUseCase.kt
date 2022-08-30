package co.kr.searchvoca.domain.usecase.word

import co.kr.searchvoca.domain.model.ErrorHandler
import co.kr.searchvoca.domain.model.Result
import co.kr.searchvoca.domain.model.Word
import co.kr.searchvoca.domain.repository.WordRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoadWordsUseCase(
    private val repo: WordRepository,
    private val handler: ErrorHandler
) {

    operator fun invoke(vocabularyId: Int? = null) = flow<Result<List<Word>>> {
        val words = if (vocabularyId == null) {
            repo.loadWords()
        } else {
            repo.loadWordsByVocabulary(vocabularyId)
        }
        emit(Result.Success(words))
    }.catch {
        emit(Result.Failure(handler.getError(it)))
    }
}