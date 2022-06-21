package co.kr.dgsw.searchvoca.viewmodel.fragment

import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.datasource.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.SingleLiveEvent

class WordTestViewModel(
    private val vocabularyRepository: VocabularyRepository,
    dispatcherProviderImpl: DispatcherProviderImpl
) : BaseViewModel(dispatcherProviderImpl) {
    val cardStackClickEvent = SingleLiveEvent<Unit>()
    val listeningTestClickEvent = SingleLiveEvent<Unit>()

    val vocabularyList = arrayListOf<VocabularyName>()

    fun getVocabularyNameList() = onIO {
        val res = vocabularyRepository.getVocabularyNames()
        vocabularyList.addAll(res)
    }

    fun navigateCardStack() {
        cardStackClickEvent.call()
    }

    fun navigateListeningTest() {
        listeningTestClickEvent.call()
    }
}