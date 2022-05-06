package co.kr.dgsw.searchvoca.viewmodel.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.kr.dgsw.searchvoca.widget.livedata.Event

class DefaultBottomSheetViewModel : ViewModel() {
    val clickedItem = MutableLiveData<Event<Pair<Int?, String>>>()
    lateinit var callback: (Pair<Int?, String>) -> Unit
}