package co.kr.dgsw.searchvoca.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.datasource.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.datasource.remote.repository.SearchRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.Event
import co.kr.dgsw.searchvoca.widget.livedata.SingleLiveEvent

class UpdateWordViewModel(
    dispatcherProvider: DispatcherProviderImpl,
    private val wordRepository: WordRepository,
    private val searchRepository: SearchRepository,
    private val vocabularyRepository: VocabularyRepository
) : BaseViewModel(dispatcherProvider) {
    val word = MutableLiveData<String>()
    val meaning = MutableLiveData<String>()
    val vocabulary = MutableLiveData<VocabularyName>()

    // 서버 값에서 받은 단어 뜻...
    val definitionList = MutableLiveData<Event<List<String>>>()

    val insertEvent = SingleLiveEvent<Unit>()
    val updateEvent = SingleLiveEvent<Unit>()
    val vocabularyTouchEvent = MutableLiveData<Event<Unit>>()

    val errorMessage = MutableLiveData<Event<String?>>()

    fun wordSearch() {
        onIO {
            val response = searchRepository.getSearchData(word.value!!).res
            val meanings = response?.map { it.definition } ?: listOf()
            definitionList.postValue(Event(meanings))
        }
    }

    fun showVocabularyDialog() {
        vocabularyTouchEvent.value = Event(Unit)
    }

    fun insertWord() {
        if (isNotBlankAllData()) {
            onIO { wordRepository.insert(getWord()) }
            insertEvent.call()
        }
    }

    fun updateWord(id: Int) {
        if (isNotBlankAllData()) {
            onIO { wordRepository.update(getWord(id)) }
            updateEvent.call()
        }
    }

    fun getVocabularyNameById(id: Int) = onIO {
        val res = vocabularyRepository.getVocabularyNameById(id)
        vocabulary.postValue(res)
    }

    private fun getWord(id: Int? = null) = Word(
        vocabulary.value?.id ?: Vocabulary.VOCABULARY_ID_NO_NAMED,
        word.value!!,
        meaning.value!!,
        id = id
    )

    private fun isNotBlankAllData(): Boolean {
        val msg = when {
            word.value.isNullOrBlank() -> "단어를 입력해주세요."
            meaning.value.isNullOrBlank() -> "뜻을 입력해주세요."
            else -> return true
        }
        errorMessage.value = Event(msg)
        return false
    }
}