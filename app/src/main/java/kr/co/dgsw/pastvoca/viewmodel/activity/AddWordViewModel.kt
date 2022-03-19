package kr.co.dgsw.pastvoca.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.dgsw.pastvoca.base.BaseViewModel
import kr.co.dgsw.pastvoca.repository.model.dto.Word
import kr.co.dgsw.pastvoca.repository.model.repository.WordRepository
import kr.co.dgsw.pastvoca.view.data.LayoutAdd

class AddWordViewModel(
    private val wordRepository: WordRepository
) : BaseViewModel() {
    val addData = LayoutAdd("댠어 추가", "단어", "뜻 (직접 입력)", "단어장 선택", true)
    val word = MutableLiveData<String>()
    val meaning = MutableLiveData<String?>()

    fun insertWord() {
        viewModelScope.launch {
            val word = Word(vocabularyId = 1, word = word.value!!, meaning = meaning.value!!)
            wordRepository.insert(word)
        }
    }
}