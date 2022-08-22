package co.kr.searchvoca.domain.usecase.word

import co.kr.searchvoca.domain.model.UiState
import co.kr.searchvoca.domain.model.Word
import co.kr.searchvoca.domain.repository.WordRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoadWordsUseCase(private val repo: WordRepository) {
    operator fun invoke(vocabularyId: Int? = null) = flow<UiState<List<Word>>> {
        val words = if (vocabularyId == null) {
            repo.loadWords()
        } else {
            repo.loadWordsByVocabulary(vocabularyId)
        }
        emit(UiState.Success(words))
    }.catch {
        emit(UiState.Failure(it))
    }
}