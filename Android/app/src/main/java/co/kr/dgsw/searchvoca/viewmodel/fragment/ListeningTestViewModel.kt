package co.kr.dgsw.searchvoca.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.kr.dgsw.searchvoca.view.data.TestWord
import co.kr.dgsw.searchvoca.widget.extension.removeSpecialSymbol
import co.kr.dgsw.searchvoca.widget.extension.removeWhiteSpaces
import co.kr.dgsw.searchvoca.widget.livedata.Event
import co.kr.dgsw.searchvoca.widget.livedata.SingleLiveEvent

class ListeningTestViewModel : ViewModel() {
    val currentWord = MutableLiveData<Event<Pair<Boolean, Int>>>()

    val wrongAnswerEvent = SingleLiveEvent<Unit>()
    val soundClickEvent = SingleLiveEvent<Unit>()

    val userAnswer = MutableLiveData<String>()
    val soundEnabled = MutableLiveData(true)
    var testWord: TestWord? = null

    fun checkAnswer() {
        val answer = testWord!!.word.convertAnswerForm()
        val userAnswer = userAnswer.value ?: "".convertAnswerForm()

        if (answer == userAnswer) {
            showNextWord(true)
        } else {
            wrongAnswerEvent.call()
        }
    }

    fun playTTS() {
        if (soundEnabled.value!!) {
            soundClickEvent.call()
        }
    }

    fun showNextWord(isCorrect: Boolean) {
        currentWord.value = Event(Pair(isCorrect, getNextIndex()))
    }

    private fun getNextIndex() =
        currentWord.value?.peekContent()?.second?.plus(1) ?: 1

    private fun String.convertAnswerForm() =
        this.removeSpecialSymbol().removeWhiteSpaces().lowercase()
}