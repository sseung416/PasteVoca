package co.kr.dgsw.searchvoca.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.datasource.remote.dto.SearchWord
import co.kr.dgsw.searchvoca.datasource.remote.repository.SearchRepository
import co.kr.dgsw.searchvoca.view.data.LayoutAdd
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.Event

class AddWordViewModel(
    dispatcherProvider: DispatcherProviderImpl,
    private val wordRepository: WordRepository,
    private val searchRepository: SearchRepository
) : BaseViewModel(dispatcherProvider) {
    val addData = LayoutAdd("단어 추가", "단어", "뜻 (직접 입력)", "단어장 선택", true)
    val searchData = MutableLiveData<Event<List<SearchWord>?>>()

    fun insertWord(word: Word) = onIO {
        wordRepository.insert(word)
    }

    fun updateWord(word: Word) = onIO {
        wordRepository.update(word)
    }

    fun getSearchData(keyword: String) = onIO {
        val response = searchRepository.getSearchData(keyword).res
        searchData.postValue(Event(response))
    }
}