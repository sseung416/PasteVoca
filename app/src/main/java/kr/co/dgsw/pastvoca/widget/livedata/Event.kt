package kr.co.dgsw.pastvoca.widget.livedata

import androidx.lifecycle.Observer

open class Event<out T>(private val content: T) {
    var hasBeenHandle = false
        private set

    fun getContentIfNotHandled(): T? =
        if (hasBeenHandle) {
            null
        } else {
            hasBeenHandle = true
            content
        }

    fun peekContent() = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(t: Event<T>?) {
        t?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}