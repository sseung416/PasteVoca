package co.kr.dgsw.searchvoca.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.Event
import co.kr.dgsw.searchvoca.widget.livedata.SingleLiveEvent
import kotlinx.coroutines.withContext

class SearchWordViewModel(
    dispatcherProviderImpl: DispatcherProviderImpl,
    private val wordRepository: WordRepository
) : BaseViewModel(dispatcherProviderImpl) {
    val words = MutableLiveData<Event<List<Word>>>()
    val update = SingleLiveEvent<Unit>()
    val delete = SingleLiveEvent<Unit>()

    fun getSearchWords() = onIO {
        val res = wordRepository.getWordsByVocabulary(Vocabulary.VOCABULARY_ID_SEARCH)
        words.postValue(Event(res))
    }

    fun updateVocabulary(ids: List<Int>, vocabularyId: Int) = onIO {
        wordRepository.updateWordVocabulary(ids, vocabularyId)
        withContext(main) { update.call() }
    }

    fun delete(ids: List<Int>) = onIO {
        wordRepository.delete(ids)
        withContext(main) { delete.call() }
    }
}