package co.kr.dgsw.searchvoca.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.datasource.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.Event

class CorrectionsListViewModel(
    private val vocabularyRepository: VocabularyRepository,
    dispatcherProviderImpl: DispatcherProviderImpl
) : BaseViewModel(dispatcherProviderImpl) {
    val correctionsList = MutableLiveData<Event<List<Vocabulary>>>()

    fun getAllCorrections() = onIO {
        val res = vocabularyRepository.getCorrectionsVocabularies()
        correctionsList.postValue(Event(res))
    }

    fun deleteCorrections(corrections: Vocabulary) = onIO {
        vocabularyRepository.delete(corrections)
    }
}