package co.kr.dgsw.searchvoca.ui.setting

import androidx.lifecycle.ViewModel
import co.kr.dgsw.searchvoca.ui.setting.SettingNavigationAction.*
import co.kr.dgsw.searchvoca.ui.tryOffer
import co.kr.searchvoca.domain.model.default
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class SettingViewModel : ViewModel(), SettingEventListener {

    val notificationDetailsVisible = MutableStateFlow(false)
    val vocabulary = MutableStateFlow(default)

    private val _navigateAction = Channel<SettingNavigationAction>()
    val navigationAction = _navigateAction.receiveAsFlow()

    override fun onBackgroundSearchSwitched(isChecked: Boolean) {
        if (isChecked) {
            _navigateAction.tryOffer(StartBackgroundSearchAction)
        } else {
            _navigateAction.tryOffer(StopBackgroundSearchAction)
        }
    }

    override fun onNotificationSwitched(isChecked: Boolean) {
        notificationDetailsVisible.value = isChecked
    }

    override fun onVocabularySelected() {
        _navigateAction.tryOffer(ShowVocabularyListAction)
    }

    override fun onFrequencySelected() {
        _navigateAction.tryOffer(ShowFrequencyListAction)
    }
}

sealed class SettingNavigationAction {
    object ShowVocabularyListAction : SettingNavigationAction()
    object ShowFrequencyListAction : SettingNavigationAction()
    object StartBackgroundSearchAction : SettingNavigationAction()
    object StopBackgroundSearchAction : SettingNavigationAction()
}

interface SettingEventListener {
    fun onBackgroundSearchSwitched(isChecked: Boolean)
    fun onNotificationSwitched(isChecked: Boolean)
    fun onVocabularySelected()
    fun onFrequencySelected()
}