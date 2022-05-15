package co.kr.dgsw.searchvoca.widget

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.widget.extension.OnClickListenerThrottled
import co.kr.dgsw.searchvoca.widget.extension.setOnClickListenerThrottled
import co.kr.dgsw.searchvoca.widget.extension.setOnTouchListenerThrottled
import co.kr.dgsw.searchvoca.widget.view.decoration.CustomDecoration
import java.text.SimpleDateFormat

// RecyclerView Decoration 추가
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

@BindingAdapter("dateText")
fun TextView.setDateText(date: Long) {
    val formatString = SimpleDateFormat("yyyy-MM-dd").format(date)
    this.text = formatString
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

@BindingAdapter(
    value = ["onClickThrottled", "throttleInterval"],
    requireAll = false
)
fun setOnClickListenerThrottled(v: View, listener: OnClickListenerThrottled, interval: Long = 1000) {
    v.setOnClickListenerThrottled(listener)
}

// 중복 클릭을 막기위함
@BindingAdapter("onTouchThrottled")
fun setOnTouchListenerThrottled(v: View, listener: OnClickListenerThrottled) {
    v.setOnTouchListenerThrottled(listener)
}