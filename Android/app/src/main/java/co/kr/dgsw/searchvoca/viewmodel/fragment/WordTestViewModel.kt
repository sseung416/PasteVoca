package co.kr.dgsw.searchvoca.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.datasource.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.Event

class WordTestViewModel(
    private val vocabularyRepository: VocabularyRepository,
    dispatcherProviderImpl: DispatcherProviderImpl
) : BaseViewModel(dispatcherProviderImpl) {
    val vocabularyNameList = MutableLiveData<Event<List<VocabularyName>>>()

    fun getVocabularyNameList() = onIO {
        val res = vocabularyRepository.getVocabularyNames()
        vocabularyNameList.postValue(Event(res))
    }
}