package co.kr.dgsw.searchvoca.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.datasource.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.datasource.remote.repository.DetectiveRepository
import co.kr.dgsw.searchvoca.widget.TextToSpeechUtil
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.Event
import com.google.protobuf.ByteString

class HomeViewModel(
    dispatcherProvider: DispatcherProviderImpl,
    private val vocabularyRepository: VocabularyRepository,
    private val wordRepository: WordRepository,
    private val detectiveRepository: DetectiveRepository
) : BaseViewModel(dispatcherProvider) {
    val vocabularyNames = MutableLiveData<Event<List<VocabularyName>>>()
    val allWords = MutableLiveData<Event<List<Word>>>()
    val wordsByVoca = MutableLiveData<Event<List<Word>>>()
    val mp3ByteString = MutableLiveData<Event<ByteString>>()

    val searchWordsCount = MutableLiveData<Int>()

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

    fun getSearchWords() = onIO {
        val res = wordRepository.getWordsByVocabulary(Vocabulary.VOCABULARY_ID_SEARCH)
        searchWordsCount.postValue(res.size)
    }

    fun updateWord(word: Word) = onIO {
        wordRepository.update(word)
    }

    fun detectWord(word: String) = onIO {
        val translateLanCode = detectiveRepository.detectLanguage(word)
        val ttsLangCode = TextToSpeechUtil.formatToTTSLanguageCode(translateLanCode)
        val byteString = TextToSpeechUtil.getSpeechByteString(word, ttsLangCode)
        mp3ByteString.postValue(Event(byteString))
    }
}