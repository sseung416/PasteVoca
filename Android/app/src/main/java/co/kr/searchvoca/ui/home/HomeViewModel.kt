package co.kr.searchvoca.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.searchvoca.domain.model.Result
import co.kr.searchvoca.domain.model.Word
import co.kr.searchvoca.domain.model.successOrNull
import co.kr.searchvoca.domain.usecase.history.GetSearchHistoryCountUseCase
import co.kr.searchvoca.domain.usecase.word.DeleteWordUseCase
import co.kr.searchvoca.domain.usecase.word.EditWordUseCase
import co.kr.searchvoca.domain.usecase.word.LoadWordsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val loadWordsUseCase: LoadWordsUseCase,
    getSearchHistoryCountUseCase: GetSearchHistoryCountUseCase,
    private val editWordUseCase: EditWordUseCase,
    private val deleteWordUseCase: DeleteWordUseCase
) : ViewModel() {

    private val _words = MutableStateFlow<List<Word>>(emptyList())
    val words: StateFlow<List<Word>> = _words

    val searchHistoryCount = getSearchHistoryCountUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Result.Loading
    )

    private var job: Job? = null

    init {
        loadWords()
    }

    fun loadWords(vocabularyId: Int? = null) {
        job?.cancel()

        job = viewModelScope.launch {
            loadWordsUseCase(vocabularyId).collect {
                _words.value = it.successOrNull() ?: emptyList()
            }
        }
    }

    // 단어 래벨 변경 메소드
    // 빠르게 변경 시... 막으랴고... 암튼 그렇다고...
    fun updateWordLevel(word: Word) {
        job?.cancel()

        job = viewModelScope.launch {
            editWordUseCase(word)
        }
    }

    fun deleteWord(id: Int) {
        viewModelScope.launch {
            deleteWordUseCase(id)
        }
    }
}