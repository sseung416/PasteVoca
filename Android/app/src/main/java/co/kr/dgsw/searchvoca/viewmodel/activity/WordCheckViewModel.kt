package co.kr.dgsw.searchvoca.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.Event

class WordCheckViewModel(
    dispatcherProvider: DispatcherProviderImpl,
    private val wordRepository: WordRepository
) : BaseViewModel(dispatcherProvider) {
    val words = MutableLiveData<Event<List<Word>>>()

    fun getWordsByVocabulary(vocaId: Int) = onIO {
        val res = wordRepository.getWordsByVocabulary(vocaId)
        words.postValue(Event(res))
    }
}