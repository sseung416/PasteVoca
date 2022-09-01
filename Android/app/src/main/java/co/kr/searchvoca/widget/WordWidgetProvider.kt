package co.kr.searchvoca.widget

//class WordWidgetProvider : AppWidgetProvider(), KoinComponent, DispatcherProvider by DispatcherProviderImpl() {
//    private val repository by inject<WordRepository>()
//
//    override fun onUpdate(
//        context: Context?,
//        appWidgetManager: AppWidgetManager?,
//        appWidgetIds: IntArray?
//    ) {
//        appWidgetIds?.forEach { appWidgetId ->
//            val widget = RemoteViews(context?.packageName, R.layout.layout_widget).apply {
//                updateListView(this, context, appWidgetManager, appWidgetId)
//            }
//
//            appWidgetManager?.updateAppWidget(appWidgetId, widget)
//        }
//    }
//
//    override fun onReceive(context: Context?, intent: Intent?) {
//        Log.e("TAG", "onReceive: ${intent?.action}", )
//        when (intent?.action) {
//            WordWidgetService.onClickWord -> Log.e("TAG", "onReceive: ", )
//        }
//        super.onReceive(context, intent)
//    }
//
//    private fun updateListView(
//        widget: RemoteViews,
//        context: Context?,
//        appWidgetManager: AppWidgetManager?,
//        appWidgetId: Int
//    ) {
//        CoroutineScope(io).launch {
//            val wordList: List<Word> = repository.getAllWords()
//            val intent = Intent(context, WordWidgetAdapter::class.java)
//                .putExtra("words", Gson().toJson(wordList))
//
////            widget.setRemoteAdapter(R.id.list_word_widget, intent)
//
////            appWidgetManager?.apply {
////                notifyAppWidgetViewDataChanged(appWidgetId, R.id.list_word_widget)
////                updateAppWidget(appWidgetId, widget)
////            }
//        }
//    }
//}