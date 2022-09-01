package co.kr.searchvoca.ui.history

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import co.kr.searchvoca.domain.model.Result
import co.kr.searchvoca.domain.model.Word
import co.kr.searchvoca.domain.model.successOrNull

@BindingAdapter("searchHistoryItems")
fun RecyclerView.bindSearchHistoryItems(result: Result<List<Word>>) {
    val adapter = this.adapter
    if (adapter is SearchHistoryListAdapter) {
        val items = result.successOrNull()?.map { History(it) }
        adapter.submitList(items)
    }
}