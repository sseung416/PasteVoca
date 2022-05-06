package co.kr.dgsw.searchvoca.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.datasource.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.Event
import co.kr.dgsw.searchvoca.widget.livedata.SingleLiveEvent

class UpdateVocabularyViewModel(
    private val vocabularyRepository: VocabularyRepository,
    dispatcherProviderImpl: DispatcherProviderImpl
) : BaseViewModel(dispatcherProviderImpl) {
    val vocabularyName = MutableLiveData<String>()
    val explaining = MutableLiveData<String>()

    val insertEvent = SingleLiveEvent<Unit>()

    val errorMessage = MutableLiveData<Event<String>>()

    fun insertVocabulary() {
        if (vocabularyName.value.isNullOrBlank()) {
            errorMessage.value = Event("단어장명을 입력해주세요.")
        } else {
            onIO { vocabularyRepository.insert(Vocabulary(vocabularyName.value!!)) }
            insertEvent.call()
        }
    }
}