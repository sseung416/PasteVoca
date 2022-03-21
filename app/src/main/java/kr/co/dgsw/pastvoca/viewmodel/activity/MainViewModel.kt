package kr.co.dgsw.pastvoca.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.dgsw.pastvoca.base.BaseViewModel
import kr.co.dgsw.pastvoca.repository.model.dto.VocabularyName
import kr.co.dgsw.pastvoca.repository.model.dto.Word
import kr.co.dgsw.pastvoca.repository.model.repository.VocabularyRepository
import kr.co.dgsw.pastvoca.repository.model.repository.WordRepository
import kr.co.dgsw.pastvoca.widget.livedata.Event

class MainViewModel(
    private val vocabularyRepository: VocabularyRepository,
    private val wordRepository: WordRepository
) : BaseViewModel() {
    val vocabularyNames = MutableLiveData<Event<List<VocabularyName>>>()
    val words = MutableLiveData<Event<List<Word>>>()

    fun getVocabularyNames() {
        viewModelScope.launch {
            vocabularyRepository.getVocabularyNames().apply {
                vocabularyNames.postValue(Event(this))
                Log.e(TAG, "getVocabularyNames: $this")
            }
        }
    }

    fun getAllWords() {
        viewModelScope.launch {
            wordRepository.getAllWords().apply {
                words.postValue(Event(this))
                Log.e(TAG, "getAllWords: $this")
            }
        }
    }

    fun getWordsByVocabulary(vocabularyId: Int) {
        viewModelScope.launch {
            wordRepository.getWordsByVocabulary(vocabularyId)
        }
    }

    fun updateWord(word: Word) {

    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}