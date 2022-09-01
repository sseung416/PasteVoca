package co.kr.searchvoca.widget

//class WordWidgetAdapter : RemoteViewsService() {
//    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
//        val list = with (object : TypeToken<List<Word>>() {}.type) {
//            val data = intent?.getStringExtra("words")
//            Gson().fromJson<List<Word>>(data, this)
//        } ?: emptyList()
//
//        return WordWidgetService(applicationContext, list)
//    }
//}
//
//class WordWidgetService(
//    private val context: Context,
//    private val list: List<Word>
//) : RemoteViewsAdapter<Word>(list) {
//    override fun getViewAt(position: Int): RemoteViews =
//        RemoteViews(context.packageName, R.layout.item_widget_word).apply {
//            setTextViewText(R.id.tv_word_widget, list[position].word)
//            setOnClickFillInIntent(R.id.tv_word_widget, getFillInIntent())
//        }
//
//    private fun getFillInIntent() = Intent().setAction(onClickWord)
//
//    companion object {
//        const val onClickWord = "onClickWord"
//    }
//}