package co.kr.dgsw.searchvoca.viewmodel.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.kr.dgsw.searchvoca.widget.livedata.Event

class TextBottomSheetViewModel : ViewModel() {
    val clickedItem = MutableLiveData<Event<Int?>>()
}