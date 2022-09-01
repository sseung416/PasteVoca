package co.kr.dgsw.searchvoca.ui.setting.service

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
import android.view.WindowManager.LayoutParams.MATCH_PARENT
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import co.kr.dgsw.searchvoca.R
import co.kr.searchvoca.domain.model.Definition
import co.kr.searchvoca.shared.domain.Level
import co.kr.searchvoca.domain.model.Result
import co.kr.searchvoca.domain.model.Word
import co.kr.searchvoca.domain.usecase.search.SearchWordUseCase
import co.kr.searchvoca.domain.usecase.word.CreateWordUseCase
import co.kr.searchvoca.shared.domain.VocabularyId
import com.airbnb.lottie.LottieAnimationView
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class FloatingSearchResultService : FloatingService(R.layout.layout_floating_search_result) {

    private val context by inject<Context>()
    private val searchWordUseCase by inject<SearchWordUseCase>()
    private val insertSearchHistoryUseCase by inject<CreateWordUseCase>()

    private val tvWord: TextView by lazy {
        viewGroup.findViewById(R.id.tv_word_floating)
    }

    private val tvMeaning: TextView by lazy {
        viewGroup.findViewById(R.id.tv_meaning_floating)
    }

    private val btnClose: ImageButton by lazy {
        viewGroup.findViewById(R.id.btn_close)
    }

    private val lottieLoading: LottieAnimationView by lazy {
        viewGroup.findViewById(R.id.lottie_loading)
    }

    private var searchJob: Job? = null

    override val windowLayoutParams: WindowManager.LayoutParams =
        getWindowParams(
            MATCH_PARENT,
            context.resources.getDimension(R.dimen.floating_search_result_height).toInt(),
            FLAG_NOT_FOCUSABLE,
            Gravity.BOTTOM
        )

    override fun init() {
        btnClose.setOnClickListener {
            stopService()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val keyword = intent?.getStringExtra("word") ?: "대충 인텐트 데이터 값이 제대로 안 왔다는 뜻"
        tvWord.text = keyword
        lottieLoading.visibility = VISIBLE

        search(keyword)

        return super.onStartCommand(intent, flags, startId)
    }

    private fun search(keyword: String) {
        searchJob?.cancel()

        searchJob = lifecycleScope.launch {
            searchWordUseCase(keyword).collect { res ->
                when (res) {
                    is Result.Success -> {
                        val formatString = res.data.formatToString()
                        tvMeaning.text = formatString
                        insertSearchHistory(keyword, formatString)
                        stopProgress()
                    }

                    is Result.Failure -> {
                        tvMeaning.text = "앗 오류인데요;"
                        stopProgress()
                    }

                    Result.Loading -> startProgress()
                }
            }
        }
    }


    private fun insertSearchHistory(keyword: String, definition: String) {
        lifecycleScope.launch {
            val word = Word(null, VocabularyId.SEARCH_HISTORY.ordinal, keyword, definition, Level.EASY)
            insertSearchHistoryUseCase(word)
        }
    }

    private fun stopProgress() {
        lottieLoading.visibility = GONE
    }

    private fun startProgress() {
        lottieLoading.visibility = VISIBLE
    }

    private fun List<Definition>.formatToString() = with(StringBuilder()) {
        if (size == 1) {
            append(this@formatToString[0])
        } else {
            for (i in indices) {
                append("${i + 1}. ${this@formatToString[i].definition}")
                if (i != lastIndex) append("\n")
            }
        }
        toString()
    }
}