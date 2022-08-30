package co.kr.searchvoca.shared.android.extension

import android.annotation.SuppressLint
import android.os.SystemClock
import android.view.View

fun interface OnClickListenerThrottled {
    fun onClickThrottled(view: View)
}

private var lastClickTime = 0L

fun View.setOnClickListenerThrottled(listener: OnClickListenerThrottled) {
    setOnClickListener {
        if (isTimePassedByInterval(1000)) {
            return@setOnClickListener
        }

        lastClickTime = SystemClock.elapsedRealtime()
        listener.onClickThrottled(this)
    }
}

@SuppressLint("ClickableViewAccessibility")
fun View.setOnTouchListenerThrottled(listener: OnClickListenerThrottled, interval: Long = 1000L) {
    setOnTouchListener { _, _ ->
        if (isTimePassedByInterval(interval)) {
            return@setOnTouchListener false
        }

        lastClickTime = SystemClock.elapsedRealtime()
        listener.onClickThrottled(this)
        return@setOnTouchListener true
    }
}

private fun isTimePassedByInterval(interval: Long) = SystemClock.elapsedRealtime() - lastClickTime < interval