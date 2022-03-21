package kr.co.dgsw.pastvoca.widget

import android.graphics.Color
import android.util.Log
import android.widget.EditText
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.dgsw.pastvoca.R
import kr.co.dgsw.pastvoca.widget.recyclerview.decoration.CustomDecoration

@BindingAdapter(
    value = ["dividerHeight", "dividerColor"],
    requireAll = false
)
fun RecyclerView.setDivider(dividerHeight: Float?, @ColorInt dividerColor: Int?) {
    CustomDecoration(
        dividerHeight ?: 0f,
        dividerColor ?: Color.TRANSPARENT
    ).apply {
        addItemDecoration(this)
    }
}

@BindingAdapter("isVisibleSearchDrawable")
fun EditText.setVisibleSearchDrawable(isVisible: Boolean) {
    val drawable = if (isVisible) context.getDrawable(R.drawable.ic_search) else null
    setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null)
}