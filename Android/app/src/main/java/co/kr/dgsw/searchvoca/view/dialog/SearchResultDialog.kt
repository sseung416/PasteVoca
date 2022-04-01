package co.kr.dgsw.searchvoca.view.dialog

import android.app.Activity
import android.os.Bundle
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.viewmodel.activity.SearchResultViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultDialog : Activity() {
    private val viewModel by viewModel<SearchResultViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result_dialog)

        val keyword = intent.getStringExtra("keyword") ?: ""
        viewModel.getSearchResult(keyword)
    }
}