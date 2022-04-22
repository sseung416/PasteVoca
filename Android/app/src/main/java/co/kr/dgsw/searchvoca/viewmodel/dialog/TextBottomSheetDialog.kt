package co.kr.dgsw.searchvoca.viewmodel.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.kr.dgsw.searchvoca.widget.livedata.Event
import co.kr.dgsw.searchvoca.widget.livedata.SingleLiveEvent

class TextBottomSheetDialog : ViewModel() {
    val clickedItem = MutableLiveData<Event<Int?>>()

    val itemClickEvent = SingleLiveEvent<Int>()
}