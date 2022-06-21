package co.kr.dgsw.searchvoca.viewmodel.dialog

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.Event
import kotlinx.coroutines.launch

class TestSettingViewModel(
    private val wordRepository: WordRepository,
    dispatcherProviderImpl: DispatcherProviderImpl
) : BaseViewModel(dispatcherProviderImpl) {
    val recyclerViewVisible = MutableLiveData(false)
    val selectVocabulary = MutableLiveData(VocabularyName(null, "전체"))
    val problemCount = MutableLiveData<String>()

    val easy = MutableLiveData(true)
    val middle = MutableLiveData(true)
    val difficult = MutableLiveData(true)

    val clickEventCancel = MutableLiveData<Event<Unit>>()
    val wordList = MutableLiveData<Event<List<Word>>>()

    val errorMessage = MutableLiveData<Event<@StringRes Int>>()

    fun setVisibleRecyclerView() {
        recyclerViewVisible.value = true
    }

    fun selectVocabulary(vocabularyName: VocabularyName) {
        selectVocabulary.value = vocabularyName
        recyclerViewVisible.value = false
    }

    fun cancel() {
        clickEventCancel.value = Event(Unit)
    }

    fun startWordTest() {
        viewModelScope.launch(io) {
            val vocabularyId = selectVocabulary.value?.id
            val wordCount = wordRepository.getWordCount(vocabularyId)
            val selectedWordCount = problemCount.value?.toInt()

            if (selectedWordCount != null && selectedWordCount > wordCount) {
                errorMessage.postValue(Event(R.string.error_message_overflow_word_count))
            } else {
                val list = if (vocabularyId == null) {
                    wordRepository.getAllWords()
                } else {
                    wordRepository.getWordsByVocabulary(selectVocabulary.value!!.id!!)
                }

                wordList.postValue(Event(list))
            }
        }
    }
}