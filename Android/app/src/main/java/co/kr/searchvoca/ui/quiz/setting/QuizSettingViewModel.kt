package co.kr.searchvoca.ui.quiz.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.searchvoca.ui.quiz.TestWord
import co.kr.searchvoca.ui.quiz.setting.QuizSettingNavigationAction.*
import co.kr.searchvoca.ui.tryOffer
import co.kr.searchvoca.shared.domain.Level
import co.kr.searchvoca.domain.model.Result
import co.kr.searchvoca.domain.model.Vocabulary
import co.kr.searchvoca.domain.model.successOr
import co.kr.searchvoca.domain.usecase.word.GetWordCountUseCase
import co.kr.searchvoca.domain.usecase.word.LoadWordsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class QuizSettingViewModel(
    private val loadWordsUseCase: LoadWordsUseCase,
    getWordCountUseCase: GetWordCountUseCase
) : ViewModel(), QuizSettingEventListener {

    val selectVocabulary = MutableStateFlow(Vocabulary(null, "전체"))

    // 유저가 설정한 퀴즈 단어 수
    val problemCount = MutableStateFlow("")

    val easy = MutableStateFlow(true)
    val medium = MutableStateFlow(true)
    val difficult = MutableStateFlow(true)

    // 선택한 단어장의 총 단어 수
    private val count = selectVocabulary.transformLatest {
        emitAll(getWordCountUseCase(it.id!!))
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Result.Loading
    )

    private val _navigateAction = Channel<QuizSettingNavigationAction>()
    val navigationAction = _navigateAction.receiveAsFlow()

    override fun onConfirmClicked() {
        viewModelScope.launch {
            val userCount = problemCount.value.toInt()
            if (userCount > count.value.successOr(0)) {
                loadWordsUseCase(selectVocabulary.value.id).collect { result ->
                    val testWords = result.successOr(emptyList())
                        .filter {
                            easy.value && (it.level == Level.EASY) ||
                                    medium.value && (it.level == Level.MEDIUM) ||
                                    difficult.value && (it.level == Level.DIFFICULT)
                        }.map { TestWord(it.word, it.definition) }
                        .shuffled()
                        .slice(0 until userCount)
                        .toTypedArray()
                    _navigateAction.tryOffer(NavigateToQuizAction(testWords))
                }
            }
        }
    }

    override fun onCancelClicked() {
        _navigateAction.tryOffer(DismissDialogAction)
    }

    override fun onSelectVocabularyClicked() {
        _navigateAction.tryOffer(ShowVocabularyListAction)
    }
}

sealed class QuizSettingNavigationAction {
    data class NavigateToQuizAction(val testWords: Array<TestWord>) : QuizSettingNavigationAction()
    object DismissDialogAction : QuizSettingNavigationAction()
    object ShowVocabularyListAction : QuizSettingNavigationAction()
}

interface QuizSettingEventListener {
    fun onConfirmClicked()
    fun onCancelClicked()
    fun onSelectVocabularyClicked()
}