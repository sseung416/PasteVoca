package co.kr.dgsw.searchvoca.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.EditText
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.ui.home.WordListAdapter
import co.kr.dgsw.searchvoca.ui.home.WordListItem
import co.kr.dgsw.searchvoca.ui.dialog.VocabularyListAdapter
import co.kr.dgsw.searchvoca.ui.dialog.VocabularyListItem
import co.kr.dgsw.searchvoca.ui.history.History
import co.kr.dgsw.searchvoca.ui.history.SearchHistoryListAdapter
import co.kr.dgsw.searchvoca.widget.view.decoration.CustomDecoration
import co.kr.searchvoca.domain.model.*
import co.kr.searchvoca.shared.android.component.LevelView
import co.kr.searchvoca.shared.android.component.OnSpeechListener
import co.kr.searchvoca.shared.android.component.QuizCardView
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

@BindingAdapter(value = ["wordItems", "historyCount"])
fun RecyclerView.bindWordItems(
    list: List<Word>,
    historyCount: Result<Int>
) {
    val adapter = this.adapter
    if (adapter is WordListAdapter) {
        val items: MutableList<WordListItem> =
            list.map { WordListItem.WordItem(it) }.toMutableList()
        val count = historyCount.successOr(0)

        if (count != 0) {
            items.add(WordListItem.SearchHistory(count))
        }

        adapter.submitList(items)
    }
}

@BindingAdapter("vocabularyItems")
fun RecyclerView.bindVocabularyItems(result: List<Vocabulary>) {
    val adapter = this.adapter
    if (adapter is VocabularyListAdapter) {
        val items: List<VocabularyListItem> = result
            .map { VocabularyListItem.VocabularyItem(it) }
        adapter.submitList(items.toMutableList())
    }
}

@BindingAdapter("searchHistoryItems")
fun RecyclerView.bindSearchHistoryItems(result: Result<List<Word>>) {
    val adapter = this.adapter
    if (adapter is SearchHistoryListAdapter) {
        val items = result.successOrNull()?.map { History(it) }
        adapter.submitList(items)
    }
}

@BindingAdapter(
    value = ["title", "icon"],
    requireAll = true
)
fun QuizCardView.bindQuizCardViewData(title: String, @DrawableRes icon: Int) {
    this.title = title
    this.iconRes = icon
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

@BindingAdapter("level")
fun LevelView.bindLevel(level: Level) {
    setLevelByName(level.name)
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