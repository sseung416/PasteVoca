package kr.co.dgsw.pastvoca.widget

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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
