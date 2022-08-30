package co.kr.dgsw.searchvoca.ui.quiz.listening

import androidx.lifecycle.ViewModel
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.ui.quiz.TestWord
import co.kr.dgsw.searchvoca.ui.quiz.listening.ListeningTestNavigationAction.*
import co.kr.dgsw.searchvoca.ui.quiz.removeSpecialSymbol
import co.kr.dgsw.searchvoca.ui.quiz.removeWhiteSpaces
import co.kr.dgsw.searchvoca.ui.tryOffer
import co.kr.searchvoca.shared.domain.Translate
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class ListeningTestViewModel : ViewModel() {

    val currentWord = MutableStateFlow(TestWord("", ""))
    val userAnswer = MutableStateFlow("")

    // todo language 설정 화면 구현
    val language = MutableStateFlow(Translate.ENGLISH)

    private val _navigateAction = Channel<ListeningTestNavigationAction>()
    val navigationAction = _navigateAction.receiveAsFlow()

    private val result = arrayListOf<Boolean>()
    private var size = 0

    fun setWordListSize(size: Int) {
        this.size = size
    }

    fun checkAnswer() {
        val answer = currentWord.value.word.convertAnswerForm()
        val userAnswer = userAnswer.value.convertAnswerForm()

        if (answer == userAnswer) {
            showNextWord(true)
        } else {
            _navigateAction.tryOffer(ShowToastAction(R.string.error_wrong_answer))
        }
    }

    fun showNextWord(isCorrect: Boolean) {
        if (result.size == size) {
            _navigateAction.tryOffer(NavigateToQuizResultAction(result))
        } else {
            userAnswer.value = ""
            result.add(isCorrect)
            _navigateAction.tryOffer(ShowNextWordAction(result.lastIndex))
        }
    }

    private fun String.convertAnswerForm() =
        this.removeSpecialSymbol().removeWhiteSpaces().lowercase()
}

sealed class ListeningTestNavigationAction {
    data class ShowToastAction(val msgResId: Int) : ListeningTestNavigationAction()
    data class ShowNextWordAction(val idx: Int) : ListeningTestNavigationAction()
    data class NavigateToQuizResultAction(val result: List<Boolean>) :
        ListeningTestNavigationAction()
}