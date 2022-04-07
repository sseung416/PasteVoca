package co.kr.dgsw.searchvoca.viewmodel.activity

import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.datasource.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.view.data.LayoutAdd
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl

class AddVocabularyViewModel(
    dispatcherProvider: DispatcherProviderImpl,
    private val vocabularyRepository: VocabularyRepository
) : BaseViewModel(dispatcherProvider) {
    val addData = LayoutAdd("단어장 추가", "그룹명 (필수)", "설명", "언어 지정", false)

    fun insertVocabulary(vocabulary: Vocabulary) = onIO {
        vocabularyRepository.insert(vocabulary)
    }
}