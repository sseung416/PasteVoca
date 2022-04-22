package co.kr.dgsw.searchvoca.view.widget

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WordWidgetAdapter : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        val list = with (object : TypeToken<List<Word>>() {}.type) {
            val data = intent?.getStringExtra("words")
            Gson().fromJson<List<Word>>(data, this)
        } ?: emptyList()

        return WordWidgetService(applicationContext, list)
    }
}

class WordWidgetService(
    private val context: Context,
    private val list: List<Word>
) : RemoteViewsAdapter<Word>(list) {
    override fun getViewAt(position: Int): RemoteViews =
        RemoteViews(context.packageName, R.layout.item_widget_word).apply {
            setTextViewText(R.id.tv_word_widget, list[position].word)
            setOnClickFillInIntent(R.id.tv_word_widget, getFillInIntent())
        }

    private fun getFillInIntent() = Intent().setAction(onClickWord)

    companion object {
        const val onClickWord = "onClickWord"
    }
}