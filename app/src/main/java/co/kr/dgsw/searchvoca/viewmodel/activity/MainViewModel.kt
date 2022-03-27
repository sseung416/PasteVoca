package co.kr.dgsw.searchvoca.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.repository.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.repository.model.dto.Word
import co.kr.dgsw.searchvoca.repository.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.repository.model.repository.WordRepository
import co.kr.dgsw.searchvoca.widget.livedata.Event

class MainViewModel(
    private val vocabularyRepository: VocabularyRepository,
    private val wordRepository: WordRepository
) : BaseViewModel() {
    val vocabularyNames = MutableLiveData<Event<List<VocabularyName>>>()
    val allWords = MutableLiveData<Event<List<Word>>>()
    val wordsByVoca = MutableLiveData<Event<List<Word>>>()

    fun getVocabularyNames() {
        viewModelScope.launch {
            vocabularyRepository.getVocabularyNames().apply {
                Log.d(TAG, "getVocabularyNames: $this")
                vocabularyNames.postValue(Event(this))
            }
        }
    }

    fun getAllWords() {
        viewModelScope.launch {
            wordRepository.getAllWords().apply {
                Log.d(TAG, "getAllWords: $this")
                allWords.postValue(Event(this))
            }
        }
    }

    fun getWordsByVocabulary(vocabularyId: Int) {
        viewModelScope.launch {
            wordRepository.getWordsByVocabulary(vocabularyId).apply {
                Log.d(TAG, "getWordsByVocabulary: $this")
                wordsByVoca.postValue(Event(this))
            }
        }
    }

    fun updateWord(word: Word) {

    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}