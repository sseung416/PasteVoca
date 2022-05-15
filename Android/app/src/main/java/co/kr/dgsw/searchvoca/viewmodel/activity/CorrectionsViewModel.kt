package co.kr.dgsw.searchvoca.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.CorrectionsWord
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.datasource.model.repository.CorrectionsWordRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.Event

class CorrectionsViewModel(
    private val correctionsWordRepository: CorrectionsWordRepository,
    dispatcherProviderImpl: DispatcherProviderImpl
) : BaseViewModel(dispatcherProviderImpl) {
    val allList = MutableLiveData<Event<List<CorrectionsWord>>>()
    private val wrongList = arrayListOf<CorrectionsWord>()
    private val correctList = arrayListOf<CorrectionsWord>()

    fun getCorrectionsWordList(vocabularyId: Int) = onIO {
        val res = correctionsWordRepository.getCorrectionsWordByVocabularyId(vocabularyId)
        allList.postValue(Event(res))
    }

    fun setList(list: List<CorrectionsWord>) {
        list.forEach {
            if (it.isCorrect) correctList.add(it)
            else wrongList.add(it)
        }
    }

    fun getList() = Triple(allList.value?.peekContent()!!, wrongList, correctList)
}