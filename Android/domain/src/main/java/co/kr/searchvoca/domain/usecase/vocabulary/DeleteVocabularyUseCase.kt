package co.kr.searchvoca.domain.usecase.vocabulary

import co.kr.searchvoca.domain.model.UiState
import co.kr.searchvoca.domain.model.Vocabulary
import co.kr.searchvoca.domain.repository.VocabularyRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class DeleteVocabularyUseCase(private val repo: VocabularyRepository) {
    operator fun invoke(vocabulary: Vocabulary) = flow<UiState<Unit>> {
        val res = repo.deleteVocabulary(vocabulary)
        emit(UiState.Success(res))
    }.catch {
        emit(UiState.Failure(it))
    }
}