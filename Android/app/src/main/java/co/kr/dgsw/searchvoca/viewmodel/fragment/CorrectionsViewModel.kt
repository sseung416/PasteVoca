package co.kr.dgsw.searchvoca.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.kr.dgsw.searchvoca.view.data.TestWord
import co.kr.dgsw.searchvoca.widget.livedata.Event

class CorrectionsViewModel : ViewModel() {
    val allList = MutableLiveData<Event<List<TestWord>>>()
    private val wrongList = arrayListOf<TestWord>()
    private val correctList = arrayListOf<TestWord>()

    fun setList(list: List<TestWord>) {
        list.forEach {
            if (it.isCorrect) correctList.add(it)
            else wrongList.add(it)
        }
    }

    fun getList() = Triple(allList.value?.peekContent()!!, wrongList, correctList)
}