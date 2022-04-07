package co.kr.dgsw.searchvoca.viewmodel.activity

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.datasource.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.view.data.LayoutAdd

class AddVocabularyViewModel(
    private val vocabularyRepository: VocabularyRepository
) : BaseViewModel() {
    val addData = LayoutAdd("단어장 추가", "그룹명 (필수)", "설명", "언어 지정", false)

    fun insertVocabulary(vocabulary: Vocabulary) {
        viewModelScope.launch {
            vocabularyRepository.insert(vocabulary)
        }
    }
}