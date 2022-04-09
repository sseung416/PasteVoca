package co.kr.dgsw.searchvoca.viewmodel.activity

import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl

class CorrectionsViewModel(
    dispatcherProviderImpl: DispatcherProviderImpl
) : BaseViewModel(dispatcherProviderImpl) {
    private val allList = arrayListOf<Word>()
    private val wrongList = arrayListOf<Word>()
    private val correctList = arrayListOf<Word>()

    fun setList(list: List<Word>) {
        allList.addAll(list)
        list.forEach {
            if (it.isCorrect) correctList.add(it)
            else wrongList.add(it)
        }
    }

    fun getList() = Triple(allList, wrongList, correctList)
}