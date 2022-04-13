package co.kr.dgsw.searchvoca.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.datasource.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.Event

class HomeViewModel(
    dispatcherProvider: DispatcherProviderImpl,
    private val vocabularyRepository: VocabularyRepository,
    private val wordRepository: WordRepository
) : BaseViewModel(dispatcherProvider) {
    val vocabularyNames = MutableLiveData<Event<List<VocabularyName>>>()
    val allWords = MutableLiveData<Event<List<Word>>>()
    val wordsByVoca = MutableLiveData<Event<List<Word>>>()

    val vocabularyId = MutableLiveData<Event<Int?>>()

    fun getVocabularyNames() = onIO {
        val res = vocabularyRepository.getVocabularyNames()
        vocabularyNames.postValue(Event(res))
    }

    fun getAllWords() = onIO {
        val res = wordRepository.getAllWords()
        allWords.postValue(Event(res))
    }

    fun getWordsByVocabulary(vocabularyId: Int) = onIO {
        val res = wordRepository.getWordsByVocabulary(vocabularyId)
        wordsByVoca.postValue(Event(res))
    }

    fun updateWord(word: Word) = onIO {
        wordRepository.update(word)
    }
}