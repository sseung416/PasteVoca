package co.kr.dgsw.searchvoca.ui.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.searchvoca.domain.model.Result
import co.kr.searchvoca.domain.model.Vocabulary
import co.kr.searchvoca.domain.model.default
import co.kr.searchvoca.domain.model.successOr
import co.kr.searchvoca.domain.usecase.vocabulary.LoadVocabulariesUseCase
import kotlinx.coroutines.flow.*

class VocabularyBottomSheetViewModel(
    loadVocabulariesUseCase: LoadVocabulariesUseCase,
) : ViewModel() {

    val vocabularies = loadVocabulariesUseCase().map {
        it.successOr(emptyList()).toMutableList().apply { add(0, default) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = listOf(default)
    )
}