package co.kr.searchvoca.ui.word

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.searchvoca.R
import co.kr.searchvoca.ui.tryOffer
import co.kr.searchvoca.ui.word.UpdateTabType.*
import co.kr.searchvoca.domain.model.*
import co.kr.searchvoca.domain.usecase.search.SearchWordUseCase
import co.kr.searchvoca.domain.usecase.vocabulary.LoadVocabulariesUseCase
import co.kr.searchvoca.domain.usecase.word.CreateWordUseCase
import co.kr.searchvoca.domain.usecase.word.EditWordUseCase
import co.kr.searchvoca.shared.domain.Level
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UpdateWordViewModel(
    private val loadVocabularyByIdUseCase: LoadVocabulariesUseCase,
    private val searchWordUseCase: SearchWordUseCase,
    private val createWordUseCase: CreateWordUseCase,
    private val editWordUseCase: EditWordUseCase,
) : ViewModel(), UpdateWordEventListener {

//    private val _uiState = MutableStateFlow<UiState<Unit>?>(null)
//    val uiState = _uiState.asStateFlow()

    private val _searchResult = MutableStateFlow<List<Definition>?>(emptyList())
    val searchResult = _searchResult.asStateFlow()

    val word = MutableStateFlow("")
    val definition = MutableStateFlow("")
    val vocabulary = MutableStateFlow(default)

    private var searchJob: Job? = null

    private var currentTabType = CREATE

    private val _error = Channel<Int>()
    val error = _error.receiveAsFlow()

    private val _navigateActions = Channel<UpdateWordNavigationAction>()
    val navigateActions = _navigateActions.receiveAsFlow()

    fun setTabType(type: UpdateTabType) {
        currentTabType = type
    }

    fun wordSearch() {
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            searchWordUseCase(word.value).collect {
                when (it) {
                    is Result.Failure -> {
                        val message = when (it.error) {
                            ErrorEntity.ApiError.Network -> R.string.error_network

                            ErrorEntity.ApiError.NotFound -> R.string.error_no_result

                            else -> R.string.error_unknown
                        }

                        _error.tryOffer(message)
                    }

                    Result.Loading -> {

                    }

                    is Result.Success -> {
                        _searchResult.value = it.successOrNull()
                    }
                }
            }
        }
    }

    fun updateWord() {
        viewModelScope.launch {
            if (word.value.isNotBlank() && definition.value.isNotBlank()) {
                val word = Word(
                    id = null,
                    vocabularyId = vocabulary.value.id ?: 0,
                    word = word.value,
                    definition = definition.value,
                    level = Level.EASY
                )

                when (currentTabType) {
                    CREATE -> createWord(word)
                    EDIT -> editWord(word)
                }
            } else {
                _error.tryOffer(R.string.error_input_field_empty)
            }
        }
    }

    fun loadVocabulary(id: Int) {
        viewModelScope.launch {
            loadVocabularyByIdUseCase(id).collect {
                vocabulary.value = it.successOr(default)
            }
        }
    }

    private suspend fun createWord(word: Word) {
        createWordUseCase(word).collect {
            _navigateActions.tryOffer(UpdateWordNavigationAction.BackAction)
        }
    }

    private suspend fun editWord(word: Word) {
        editWordUseCase(word).collect {
            _navigateActions.tryOffer(UpdateWordNavigationAction.BackAction)
        }
    }

    override fun onVocabularyChoiceClicked() {
        _navigateActions.tryOffer(UpdateWordNavigationAction.ShowVocabularyAction)
    }
}

sealed class UpdateWordNavigationAction {
    object ShowVocabularyAction : UpdateWordNavigationAction()
    object BackAction : UpdateWordNavigationAction()
}

interface UpdateWordEventListener {
    fun onVocabularyChoiceClicked()
}