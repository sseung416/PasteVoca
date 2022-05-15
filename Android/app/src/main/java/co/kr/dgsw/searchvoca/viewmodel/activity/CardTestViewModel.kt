package co.kr.dgsw.searchvoca.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.CorrectionsWord
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.datasource.model.repository.CorrectionsWordRepository
import co.kr.dgsw.searchvoca.datasource.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.Event
import co.kr.dgsw.searchvoca.widget.view.CardStackAdapter

class CardTestViewModel(
    dispatcherProvider: DispatcherProviderImpl,
    private val wordRepository: WordRepository,
    private val vocabularyRepository: VocabularyRepository,
    private val correctionsWordRepository: CorrectionsWordRepository
) : BaseViewModel(dispatcherProvider), CardStackAdapter {
    val words = MutableLiveData<Event<List<Word>>>()
    val correctionsVocabularyId = MutableLiveData<Event<Int>>()

    fun getAllWords() = onIO {
        val res = wordRepository.getAllWords()
        words.postValue(Event(res))
    }

    fun getWordsByVocabulary(vocaId: Int) = onIO {
        val res = wordRepository.getWordsByVocabulary(vocaId)
        words.postValue(Event(res))
    }

    fun insertVocabulary(data: Vocabulary) = onIO {
        vocabularyRepository.insert(data)
    }

    fun insertCorrectionsWord(data: CorrectionsWord) = onIO {
        correctionsWordRepository.insert(data)
    }

    fun getLastCorrectionsVocabularyId() = onIO {
        val id = vocabularyRepository.getLastCorrectionsVocabularyId()
        correctionsVocabularyId.postValue(Event(id))
    }
}