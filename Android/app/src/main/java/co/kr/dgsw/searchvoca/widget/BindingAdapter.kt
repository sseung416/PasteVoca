package co.kr.dgsw.searchvoca.widget

import android.graphics.Color
import android.widget.EditText
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.R
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

@BindingAdapter("isVisibleSearchDrawable")
fun EditText.setVisibleSearchDrawable(isVisible: Boolean) {
    val drawable = if (isVisible) context.getDrawable(R.drawable.ic_search) else null
    setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null)
}