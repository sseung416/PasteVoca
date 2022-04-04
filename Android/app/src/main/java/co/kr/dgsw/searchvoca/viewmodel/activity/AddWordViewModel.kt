package co.kr.dgsw.searchvoca.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.repository.model.dto.Word
import co.kr.dgsw.searchvoca.repository.model.repository.WordRepository
import co.kr.dgsw.searchvoca.repository.remote.dto.SearchWord
import co.kr.dgsw.searchvoca.repository.remote.repository.SearchRepository
import co.kr.dgsw.searchvoca.view.data.LayoutAdd
import co.kr.dgsw.searchvoca.widget.livedata.Event

class AddWordViewModel(
    private val wordRepository: WordRepository,
    private val searchRepository: SearchRepository
) : BaseViewModel() {
    val addData = LayoutAdd("단어 추가", "단어", "뜻 (직접 입력)", "단어장 선택", true)
    val searchData = MutableLiveData<Event<List<SearchWord>?>>()

    fun insertWord(word: Word) {
        viewModelScope.launch {
            wordRepository.insert(word)
        }
    }

    fun updateWord(word: Word) {
        viewModelScope.launch {
            wordRepository.update(word)
        }
    }

    fun getSearchData(keyword: String) {
        viewModelScope.launch {
            val response = searchRepository.getSearchData(keyword).res
            searchData.postValue(Event(response))
        }
    }
}