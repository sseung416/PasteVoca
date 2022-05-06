package co.kr.dgsw.searchvoca.viewmodel.dialog

import androidx.lifecycle.MutableLiveData
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.datasource.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.Event

class VocabularyBottomSheetViewModel(
    dispatcherProviderImpl: DispatcherProviderImpl,
    private val vocabularyRepository: VocabularyRepository
) : BaseViewModel(dispatcherProviderImpl) {
    val clickedItem = MutableLiveData<Event<VocabularyName>>()

    val vocabularyNameList = MutableLiveData<Event<List<VocabularyName>>>()

    fun getVocabulary() = onIO {
        val response = vocabularyRepository.getVocabularyNames()
        vocabularyNameList.postValue(Event(response))
    }
}