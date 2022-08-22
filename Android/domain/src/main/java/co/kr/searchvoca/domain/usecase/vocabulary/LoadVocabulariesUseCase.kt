package co.kr.searchvoca.domain.usecase.vocabulary

import co.kr.searchvoca.domain.model.UiState
import co.kr.searchvoca.domain.model.Vocabulary
import co.kr.searchvoca.domain.repository.VocabularyRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoadVocabulariesUseCase(private val repo: VocabularyRepository) {
    operator fun invoke() = flow<UiState<List<Vocabulary>>> {
        val vocabularies = repo.loadVocabularies()
        emit(UiState.Success(vocabularies))
    }.catch {
        emit(UiState.Failure(it))
    }
}