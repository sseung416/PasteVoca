package co.kr.dgsw.searchvoca.widget

import android.graphics.Color
import android.widget.EditText
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.widget.recyclerview.decoration.CustomDecoration

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