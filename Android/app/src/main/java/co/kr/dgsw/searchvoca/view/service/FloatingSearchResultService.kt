package co.kr.dgsw.searchvoca.view.service

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.WindowManager
import co.kr.dgsw.searchvoca.R
import org.koin.android.ext.android.inject
import android.view.WindowManager.LayoutParams.MATCH_PARENT
import android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
import android.widget.ImageButton
import android.widget.TextView
import co.kr.dgsw.searchvoca.datasource.remote.dto.SearchWord
import co.kr.dgsw.searchvoca.datasource.remote.repository.SearchRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import kotlinx.coroutines.*
import java.lang.StringBuilder
import kotlin.coroutines.CoroutineContext

class FloatingSearchResultService : FloatingService(), CoroutineScope {
    private val dispatcherProvider by inject<DispatcherProviderImpl>()
    private val repository by inject<SearchRepository>()
    private val context by inject<Context>()

    private val job: Job by lazy { Job() }
    override val coroutineContext: CoroutineContext by lazy { dispatcherProvider.io + job }

    private lateinit var tvWord: TextView
    private lateinit var tvMeaning: TextView
    private lateinit var btnClose: ImageButton

    private lateinit var searchData: List<SearchWord>

    override val windowLayoutParams: WindowManager.LayoutParams =
        getWindowParams(
            MATCH_PARENT,
            context.resources.getDimension(R.dimen.floating_search_result_height).toInt(),
            FLAG_NOT_FOCUSABLE,
            Gravity.BOTTOM
        )

    override fun getLayoutRes(): Int = R.layout.layout_floating_search_result

    override fun init() {
        setupView()

        btnClose.setOnClickListener {
            stopService()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val keyword = intent?.getStringExtra("word") ?: "대충 인텐트 데이터 값이 제대로 안 왔다는 뜻"
        tvWord.text = keyword
        // todo 로딩 화면 만들기
        CoroutineScope(coroutineContext).launch(Dispatchers.Main) {
            getSearchData(keyword)
            tvMeaning.text = format(searchData)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private fun setupView() {
        tvWord = viewGroup.findViewById(R.id.tv_word_floating)
        tvMeaning = viewGroup.findViewById(R.id.tv_meaning_floating)
        btnClose = viewGroup.findViewById(R.id.btn_close)
    }

    private suspend fun getSearchData(keyword: String) =
        withContext(coroutineContext) {
            searchData = repository.getSearchData(keyword).res!!
        }

    private fun format(res: List<SearchWord>) = with (StringBuilder()) {
        for (i in res.indices) {
            this.append("${i+1}. ${res[i].definition}\n")
        }
        toString()
    }
}