package co.kr.dgsw.searchvoca.viewmodel.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.Event
import kotlinx.coroutines.launch

class TestSettingViewModel(
    private val wordRepository: WordRepository,
    dispatcherProviderImpl: DispatcherProviderImpl
) : BaseViewModel(dispatcherProviderImpl) {
    val recyclerViewVisible = MutableLiveData(false)
    val selectVocabularyName = MutableLiveData(VocabularyName(null, "전체"))
    val problemCount = MutableLiveData<String>()
    val easy = MutableLiveData(true)
    val middle = MutableLiveData(true)
    val difficult = MutableLiveData(true)

    val clickEventCancel = MutableLiveData<Event<Unit>>()
    val clickEventConfirm = MutableLiveData<Event<Unit>>()

    val errorMessage = MutableLiveData<Event<String>>()

    fun setVisibleRecyclerView() {
        recyclerViewVisible.value = true
    }

    fun select(vocabularyName: VocabularyName) {
        selectVocabularyName.value = vocabularyName
        recyclerViewVisible.value = false
    }

    fun cancel() {
        clickEventCancel.value = Event(Unit)
    }

    fun startWordTest() {
        viewModelScope.launch(io) {
            val vocabularyId = selectVocabularyName.value?.id
            val selectedCount = problemCount.value?.toInt()

            val count = if (vocabularyId == null) {
                wordRepository.getWordCount()
            } else {
                wordRepository.getWordCount(vocabularyId)
            }

            if (selectedCount == null || selectedCount > count) {
                errorMessage.postValue(Event("단어 갯수가 느무 많아염;;"))
            } else {
                clickEventConfirm.postValue(Event(Unit))
            }
        }
    }
}