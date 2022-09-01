package co.kr.searchvoca.ui.vocabulary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.searchvoca.R
import co.kr.searchvoca.ui.tryOffer
import co.kr.searchvoca.ui.word.UpdateTabType
import co.kr.searchvoca.ui.word.UpdateTabType.CREATE
import co.kr.searchvoca.ui.word.UpdateTabType.EDIT
import co.kr.searchvoca.domain.model.Vocabulary
import co.kr.searchvoca.domain.usecase.vocabulary.CreateVocabularyUseCase
import co.kr.searchvoca.domain.usecase.vocabulary.EditVocabularyUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UpdateVocabularyViewModel(
    private val createVocabularyUseCase: CreateVocabularyUseCase,
    private val editVocabularyUseCase: EditVocabularyUseCase
) : ViewModel() {

    val name = MutableStateFlow("")
    val explaining = MutableStateFlow("")

    private val _error = Channel<Int>()
    val error = _error.receiveAsFlow()

    private val _navigateActions = Channel<UpdateVocabularyAction>()
    val navigateAction = _navigateActions.receiveAsFlow()

    private var currentTabType = CREATE

    fun setTabType(type: UpdateTabType) {
        currentTabType = type
    }

    fun updateVocabulary() {
        viewModelScope.launch {
            if (name.value.isNotBlank()) {
                val vocabulary = Vocabulary(
                    id = null,
                    name = name.value,
                    explanation = explaining.value
                )

                when (currentTabType) {
                    CREATE -> createVocabulary(vocabulary)
                    EDIT -> editVocabulary(vocabulary)
                }
            } else {
                _error.tryOffer(R.string.error_input_field_empty)
            }
        }
    }

    private suspend fun createVocabulary(vocabulary: Vocabulary) {
        createVocabularyUseCase(vocabulary).collect {
            _navigateActions.tryOffer(UpdateVocabularyAction.BackAction)
        }
    }

    private suspend fun editVocabulary(vocabulary: Vocabulary) {
        editVocabularyUseCase(vocabulary).collect {
            _navigateActions.tryOffer(UpdateVocabularyAction.BackAction)
        }
    }
}

sealed class UpdateVocabularyAction {
    object BackAction : UpdateVocabularyAction()
}