package co.kr.searchvoca.ui.dialog.vocabulary

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import co.kr.searchvoca.domain.model.Vocabulary


@BindingAdapter("vocabularyItems")
fun RecyclerView.bindVocabularyItems(result: List<Vocabulary>) {
    val adapter = this.adapter
    if (adapter is VocabularyListAdapter) {
        val items: List<VocabularyListItem> = result
            .map { VocabularyListItem.VocabularyItem(it) }
        adapter.submitList(items.toMutableList())
    }
}
