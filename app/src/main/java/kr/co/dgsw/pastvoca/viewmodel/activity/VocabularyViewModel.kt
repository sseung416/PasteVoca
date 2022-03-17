package kr.co.dgsw.pastvoca.viewmodel.activity

import kr.co.dgsw.pastvoca.base.BaseViewModel
import kr.co.dgsw.pastvoca.repository.model.repository.VocabularyRepository

class VocabularyViewModel(
    private val vocabularyRepository: VocabularyRepository
) : BaseViewModel() {

    fun insertVoca() {
//        vocabularyRepository.insert()
    }
}