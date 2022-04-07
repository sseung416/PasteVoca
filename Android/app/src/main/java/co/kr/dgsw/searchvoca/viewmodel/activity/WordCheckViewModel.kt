package co.kr.dgsw.searchvoca.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.widget.livedata.Event
import kotlinx.coroutines.launch

class WordCheckViewModel(
    private val wordRepository: WordRepository
) : BaseViewModel() {
    val words = MutableLiveData<Event<List<Word>>>()

    fun getWordsByVocabulary(vocaId: Int) {
        viewModelScope.launch {
            val res = wordRepository.getWordsByVocabulary(vocaId)
            words.postValue(Event(res))
        }
    }
}