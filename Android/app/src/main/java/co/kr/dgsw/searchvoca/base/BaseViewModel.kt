package co.kr.dgsw.searchvoca.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProvider
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel(
    dispatcherProviderImpl: DispatcherProviderImpl
) : ViewModel(), DispatcherProvider by dispatcherProviderImpl {

    inline fun BaseViewModel.onMain(
        crossinline body: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(main) {
        body(this)
    }

    inline fun BaseViewModel.onIO(
        crossinline body: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(io) {
        body(this)
    }

    inline fun BaseViewModel.onDefault(
        crossinline body: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(default) {
        body(this)
    }
}