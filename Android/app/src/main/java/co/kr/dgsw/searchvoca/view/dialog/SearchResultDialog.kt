package co.kr.dgsw.searchvoca.view.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.viewmodel.activity.SearchResultViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultDialog : AppCompatActivity() {
    private val viewModel by viewModel<SearchResultViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result_dialog)

        intent.getStringExtra("keyword")

        viewModel.getSearchResult("고구마")
    }
}