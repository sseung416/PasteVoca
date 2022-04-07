package co.kr.dgsw.searchvoca.viewmodel.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.widget.livedata.SingleLiveEvent
import kotlinx.coroutines.launch

class WordBottomSheetViewModel(
    private val wordRepository: WordRepository
) : ViewModel() {
    val deleteEvent = SingleLiveEvent<Unit>()

    fun deleteWord(word: Word) {
        viewModelScope.launch {
            wordRepository.delete(word)
        }
        deleteEvent.call()
    }
}