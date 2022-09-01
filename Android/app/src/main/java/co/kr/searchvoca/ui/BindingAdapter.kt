package co.kr.searchvoca.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.EditText
import androidx.annotation.ColorInt
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import co.kr.searchvoca.widget.view.decoration.CustomDecoration
import co.kr.searchvoca.shared.android.component.OnSpeechListener
import co.kr.searchvoca.shared.android.component.TextToSpeechButton
import co.kr.searchvoca.shared.android.extension.*
import co.kr.searchvoca.shared.domain.Translate

@BindingAdapter("dialog")
fun View.bindDialog(throwable: Throwable?) {
    throwable?.message?.let { errorMessage ->
        AlertDialog.Builder(context)
            .setMessage(errorMessage)
            .create()
            .show()
    }
}

@BindingAdapter(value = ["dividerHeight", "dividerColor", "dividerPadding"])
fun RecyclerView.bindItemDecoration(
    dividerHeight: Float?,
    @ColorInt dividerColor: Int?,
    dividerPadding: Float?
) {
    val decoration = CustomDecoration(
        dividerHeight ?: 0f,
        dividerColor ?: Color.BLACK,
        dividerPadding ?: 0f
    )
    this.addItemDecoration(decoration)
}

@BindingAdapter("adapter")
fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>?) {
    this.adapter = adapter
}

@BindingAdapter(value = ["onSpeechStart", "onSpeechDone"])
fun TextToSpeechButton.bindOnSpeechListener(
    onSpeechStart: (() -> Unit)?,
    onSpeechDone: (() -> Unit)?
) {
    setOnSpeechListener(object : OnSpeechListener {
        override fun onStart() {
            onSpeechStart?.invoke()
        }

        override fun onDone() {
            onSpeechDone?.invoke()
        }
    })
}

@BindingAdapter(
    value = ["speechText", "language"],
    requireAll = true
)
fun TextToSpeechButton.bindSpeechText(speechText: String, language: Translate) {
    this.text = speechText
    this.language = language
}


@BindingAdapter("visible")
fun View.setVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("onClickThrottled")
fun setOnClickListenerThrottled(
    v: View,
    listener: OnClickListenerThrottled
) {
    v.setOnClickListenerThrottled(listener)
}

@BindingAdapter("onTouchThrottled")
fun setOnTouchListenerThrottled(v: View, listener: OnClickListenerThrottled) {
    v.setOnTouchListenerThrottled(listener)
}

@BindingAdapter("onDrawableEndClick")
@SuppressLint("ClickableViewAccessibility")
fun EditText.bindOnDrawableEndClickListener(listener: OnDrawableEndClickListener) {
    this.setOnDrawableEndClickListener(listener)
}