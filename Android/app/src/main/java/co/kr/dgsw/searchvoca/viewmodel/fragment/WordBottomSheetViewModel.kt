package co.kr.dgsw.searchvoca.viewmodel.fragment

import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.SingleLiveEvent
import kotlinx.coroutines.withContext

class WordBottomSheetViewModel(
    dispatcherProvider: DispatcherProviderImpl,
    private val wordRepository: WordRepository
) : BaseViewModel(dispatcherProvider) {
    val deleteEvent = SingleLiveEvent<Unit>()

    fun deleteWord(word: Word) = onIO {
        wordRepository.delete(word)
        withContext(main){ deleteEvent.call() }
    }
}