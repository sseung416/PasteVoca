package co.kr.dgsw.searchvoca.widget

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.widget.view.decoration.CustomDecoration

@BindingAdapter(
    value = ["dividerHeight", "dividerColor"],
    requireAll = false
)
fun RecyclerView.setDivider(dividerHeight: Float?, @ColorInt dividerColor: Int?) {
    val decoration = CustomDecoration(
        dividerHeight ?: 0f,
        dividerColor ?: Color.BLACK,
        0f
    )
    addItemDecoration(decoration)
}

@BindingAdapter("visible")
fun View.setVisible(isVisible: Boolean) {
    visibility = if (isVisible) VISIBLE else GONE
}

// 전달받은 파라미터의 리스트가 비어있을 때 visible 처리
@BindingAdapter("visible")
fun View.setVisible(size: Int) {
    visibility = if (size != 0) VISIBLE else GONE
}

@BindingAdapter("onDrawableEndClick")
@SuppressLint("ClickableViewAccessibility")
fun EditText.setOnDrawableEndClickListener(listener: () -> Unit) {
    setOnTouchListener { view, motionEvent ->
        view as EditText
        if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            if (motionEvent.rawX >= (view.width - view.compoundDrawables[2].bounds.width())) {
                listener()
                return@setOnTouchListener true
            }
        }

        return@setOnTouchListener false
    }
}

@BindingAdapter("onTouch")
@SuppressLint("ClickableViewAccessibility")
fun setOnTouchListener(view: View, listener: View.OnTouchListener) {
    view.setOnTouchListener { v, motionEvent ->
        listener.onTouch(v, motionEvent)
    }
}

var lastClickTime = 0L

// 중복 클릭을 막기위함
@BindingAdapter(
    value = ["onClickThrottled", "throttleInterval"],
    requireAll = false
)
fun setOnClickListenerThrottled(view: View, listener: View.OnClickListener, interval: Long = 1000) {
    view.setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < interval) {
            return@setOnClickListener
        }

        lastClickTime = SystemClock.elapsedRealtime()
        listener.onClick(view)
    }
}

// 중복 클릭을 막기위함
@BindingAdapter("onTouchThrottled")
@SuppressLint("ClickableViewAccessibility")
fun View.setOnTouchListenerThrottled(listener: Test) {
    setOnTouchListener { _, _ ->
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) {
            return@setOnTouchListener false
        }

        lastClickTime = SystemClock.elapsedRealtime()
        listener.onClick()
        return@setOnTouchListener true
    }
}

interface Test {
    fun onClick()
}