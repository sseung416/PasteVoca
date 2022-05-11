package co.kr.dgsw.searchvoca.viewmodel.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName

class TestSettingViewModel : ViewModel() {
    val recyclerViewVisible = MutableLiveData(false)
    val selectVocabularyName = MutableLiveData(VocabularyName(null, "전체"))
    val problemCount = MutableLiveData<String>()
    val easy = MutableLiveData(true)
    val middle = MutableLiveData(true)
    val difficult = MutableLiveData(true)

    fun setVisibleRecyclerView() {
        recyclerViewVisible.value = true
    }

    fun select(vocabularyName: VocabularyName) {
        selectVocabularyName.value = vocabularyName
        recyclerViewVisible.value = false
    }
}