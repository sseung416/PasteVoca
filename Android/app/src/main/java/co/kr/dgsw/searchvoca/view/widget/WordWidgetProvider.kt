package co.kr.dgsw.searchvoca.view.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProvider
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WordWidgetProvider : AppWidgetProvider(), KoinComponent, DispatcherProvider by DispatcherProviderImpl() {
    private val repository by inject<WordRepository>()

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            val widget = RemoteViews(context?.packageName, R.layout.layout_widget).apply {
                updateListView(this, context, appWidgetManager, appWidgetId)
            }

            appWidgetManager?.updateAppWidget(appWidgetId, widget)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("TAG", "onReceive: ${intent?.action}", )
        when (intent?.action) {
            WordWidgetService.onClickWord -> Log.e("TAG", "onReceive: ", )
        }
        super.onReceive(context, intent)
    }

    private fun updateListView(
        widget: RemoteViews,
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int
    ) {
        CoroutineScope(io).launch {
            val wordList: List<Word> = repository.getAllWords()
            val intent = Intent(context, WordWidgetAdapter::class.java)
                .putExtra("words", Gson().toJson(wordList))

//            widget.setRemoteAdapter(R.id.list_word_widget, intent)

//            appWidgetManager?.apply {
//                notifyAppWidgetViewDataChanged(appWidgetId, R.id.list_word_widget)
//                updateAppWidget(appWidgetId, widget)
//            }
        }
    }
}