package co.kr.dgsw.searchvoca.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.dgsw.searchvoca.ui.tryOffer
import co.kr.searchvoca.domain.model.Result
import co.kr.searchvoca.domain.usecase.word.DeleteWordUseCase
import co.kr.searchvoca.domain.usecase.word.EditWordUseCase
import co.kr.searchvoca.domain.usecase.history.LoadSearchHistoryUseCase
import co.kr.searchvoca.shared.domain.VocabularyId
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchHistoryViewModel(
    loadSearchHistoryUseCase: LoadSearchHistoryUseCase,
    private val editWordUseCase: EditWordUseCase,
    private val deleteWordUseCase: DeleteWordUseCase
) : ViewModel() {

    val history = loadSearchHistoryUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        Result.Loading
    )

    private val _action = Channel<SearchHistoryAction>()
    val action = _action.receiveAsFlow()

    fun addWord(ids: List<Int>) {
        // todo 추가할 단어의 단어장 선택하게 하기
        viewModelScope.launch {
            editWordUseCase(ids, VocabularyId.NO_NAMED.ordinal).collect {
                _action.tryOffer(SearchHistoryAction.RemoveCheckedItems)
            }
        }
    }

    fun deleteHistory(ids: List<Int>) {
        viewModelScope.launch {
            deleteWordUseCase(ids).collect {
                _action.tryOffer(SearchHistoryAction.RemoveCheckedItems)
            }
        }
    }
}

sealed class SearchHistoryAction {
    object RemoveCheckedItems : SearchHistoryAction()
}