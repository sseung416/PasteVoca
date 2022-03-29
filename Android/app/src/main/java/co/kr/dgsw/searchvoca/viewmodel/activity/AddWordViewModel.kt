package co.kr.dgsw.searchvoca.viewmodel.activity

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.repository.model.dto.Word
import co.kr.dgsw.searchvoca.repository.model.repository.WordRepository
import co.kr.dgsw.searchvoca.view.data.LayoutAdd

class AddWordViewModel(
    private val wordRepository: WordRepository
) : BaseViewModel() {
    val addData = LayoutAdd("단어 추가", "단어", "뜻 (직접 입력)", "단어장 선택", true)

    fun insertWord(word: Word) {
        viewModelScope.launch {
            wordRepository.insert(word)
        }
    }
}