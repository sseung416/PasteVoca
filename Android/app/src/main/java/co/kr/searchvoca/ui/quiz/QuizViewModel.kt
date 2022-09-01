package co.kr.searchvoca.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.searchvoca.ui.tryOffer
import co.kr.searchvoca.domain.model.*
import co.kr.searchvoca.domain.usecase.vocabulary.LoadVocabulariesUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

class QuizViewModel(
    loadVocabulariesUseCase: LoadVocabulariesUseCase
) : ViewModel(), QuizEventListener {

    val vocabularies = loadVocabulariesUseCase().map {
        it.successOr(emptyList()).toMutableList().apply {
            add(default)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    private val _navigateActions = Channel<QuizNavigationAction>()
    val navigationAction = _navigateActions.receiveAsFlow()

    override fun onCardTestClicked() {
        _navigateActions.tryOffer(QuizNavigationAction.NavigateToCardTestAction)
    }

    override fun onListeningTestClicked() {
        _navigateActions.tryOffer(QuizNavigationAction.NavigateToListeningTestAction)
    }
}

sealed class QuizNavigationAction {
    object NavigateToListeningTestAction : QuizNavigationAction()
    object NavigateToCardTestAction : QuizNavigationAction()
}

interface QuizEventListener {
    fun onCardTestClicked()
    fun onListeningTestClicked()
}