package co.kr.dgsw.searchvoca.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import co.kr.searchvoca.domain.model.Result
import co.kr.searchvoca.domain.model.Word
import co.kr.searchvoca.domain.model.successOr
import co.kr.searchvoca.shared.android.component.LevelView
import co.kr.searchvoca.shared.domain.Level

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

@BindingAdapter("level")
fun LevelView.bindLevel(level: Level) {
    setLevelByName(level.name)
}