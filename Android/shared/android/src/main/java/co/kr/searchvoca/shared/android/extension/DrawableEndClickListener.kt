package co.kr.searchvoca.shared.android.extension

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.widget.EditText

fun interface OnDrawableEndClickListener {
    fun onDrawableEndClick(view: View)
}

@SuppressLint("ClickableViewAccessibility")
fun EditText.setOnDrawableEndClickListener(listener: OnDrawableEndClickListener) {
    setOnTouchListener { _, motionEvent ->
        if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            if (motionEvent.rawX >= (this.width - this.compoundDrawables[2].bounds.width())) {
                listener.onDrawableEndClick(this)
                return@setOnTouchListener true
            }
        }

        return@setOnTouchListener false
    }
}