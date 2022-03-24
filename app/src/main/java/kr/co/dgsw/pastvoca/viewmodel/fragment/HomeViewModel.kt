package kr.co.dgsw.pastvoca.viewmodel.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.dgsw.pastvoca.base.BaseViewModel
import kr.co.dgsw.pastvoca.repository.model.dto.VocabularyName
import kr.co.dgsw.pastvoca.repository.model.dto.Word
import kr.co.dgsw.pastvoca.repository.model.repository.VocabularyRepository
import kr.co.dgsw.pastvoca.repository.model.repository.WordRepository
import kr.co.dgsw.pastvoca.widget.livedata.Event

class HomeViewModel(
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
        private const val TAG = "HomeViewModel"
    }
}