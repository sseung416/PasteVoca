package kr.co.dgsw.pastvoca.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.dgsw.pastvoca.base.BaseViewModel
import kr.co.dgsw.pastvoca.repository.model.dto.Word
import kr.co.dgsw.pastvoca.repository.model.repository.VocabularyRepository
import kr.co.dgsw.pastvoca.repository.model.repository.WordRepository
import kr.co.dgsw.pastvoca.widget.livedata.Event

class MainViewModel(
    private val vocabularyRepository: VocabularyRepository,
    private val wordRepository: WordRepository
) : BaseViewModel() {
    val vocabularyNames = MutableLiveData<Event<List<String>>>()
    val words = MutableLiveData<Event<List<Word>>>()

    fun getVocabularyNames() {
        viewModelScope.launch {
            vocabularyNames.postValue(Event(vocabularyRepository.getVocabularyNames()))
        }
    }

    fun getAllWords() {
        viewModelScope.launch {
            words.postValue(Event(wordRepository.getAllWords()))
        }
    }

    fun updateWord(word: Word) {

    }
}