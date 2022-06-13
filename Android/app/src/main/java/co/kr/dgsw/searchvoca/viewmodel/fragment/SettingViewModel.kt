package co.kr.dgsw.searchvoca.viewmodel.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.kr.dgsw.searchvoca.widget.livedata.Event

class SettingViewModel : ViewModel() {
    val notificationDetailsVisible = MutableLiveData(false)

    val clickFrequencyEvent = MutableLiveData<Event<Boolean>>()
    val clickVocabularyChoiceEvent = MutableLiveData<Event<Boolean>>()

    fun setVisibleNotificationDetails(check: Boolean) {
        notificationDetailsVisible.value = check
    }

    fun showNotificationFrequencyDialog() {
        clickFrequencyEvent.value = Event(true)
    }

    fun showVocabularyChoiceDialog() {
        clickVocabularyChoiceEvent.value = Event(true)
    }
}