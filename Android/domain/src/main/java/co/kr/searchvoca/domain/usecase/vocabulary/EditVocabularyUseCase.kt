package co.kr.searchvoca.domain.usecase.vocabulary

import co.kr.searchvoca.domain.model.UiState
import co.kr.searchvoca.domain.model.Vocabulary
import co.kr.searchvoca.domain.repository.VocabularyRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class EditVocabularyUseCase(private val repo: VocabularyRepository) {
    operator fun invoke(vocabulary: Vocabulary) = flow<UiState<Unit>> {
        val res = repo.editVocabulary(vocabulary)
        emit(UiState.Success(res))
    }.catch {
        emit(UiState.Failure(it))
    }
}