package co.kr.searchvoca.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.kr.searchvoca.R
import co.kr.searchvoca.databinding.ItemSearchHistoryBinding
import co.kr.searchvoca.databinding.ItemWordBinding
import co.kr.searchvoca.ui.executeAfter
import co.kr.searchvoca.domain.model.Word
import co.kr.searchvoca.shared.android.extension.setOnClickListenerThrottled
import co.kr.searchvoca.shared.android.next

class WordListAdapter(
    private val onLongClickWord: (Word) -> Boolean,
    private val onClickSearchHistory: () -> Unit,
    private val onClickLevel: (Word) -> Unit
) : ListAdapter<WordListItem, WordListViewHolder>(diffCallback) {

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is WordListItem.SearchHistory -> R.layout.item_search_history

            is WordListItem.WordItem -> R.layout.item_word
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolder =
        when (viewType) {
            R.layout.item_search_history ->
                WordListViewHolder.SearchHistoryViewHolder(
                    ItemSearchHistoryBinding.inflate(LayoutInflater.from(parent.context)),
                    onClickSearchHistory
                )

            else ->
                WordListViewHolder.WordItemViewHolder(
                    ItemWordBinding.inflate(LayoutInflater.from(parent.context)).apply {
                        this.root.layoutParams = RecyclerView.LayoutParams(
                            MATCH_PARENT,
                            WRAP_CONTENT
                        )
                    }
                )
        }

    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is WordListViewHolder.WordItemViewHolder ->
                holder.bind(item as WordListItem.WordItem, onLongClickWord, onClickLevel)

            is WordListViewHolder.SearchHistoryViewHolder ->
                holder.bind(item as WordListItem.SearchHistory)
        }
    }

    fun sort(type: SortType) {
        val list = currentList.toMutableList()
        when (type) {
            SortType.SHUFFLE -> list.itemShuffle()
            SortType.LEVEL_DIFFICULT -> list.sortByLevelDifficult()
            SortType.LEVEL_EASY -> list.sortByLevelEasy()
        }

        submitList(list)
    }

    fun remove(item: WordListItem) {
        val list = currentList.toMutableList()
        list.remove(item)
        submitList(list)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<WordListItem>() {
            override fun areItemsTheSame(oldItem: WordListItem, newItem: WordListItem): Boolean {
                val isSameSearchHistory = oldItem is WordListItem.SearchHistory
                        && newItem is WordListItem.SearchHistory
                        && oldItem.count == newItem.count

                val isSameWord = oldItem is WordListItem.WordItem
                        && newItem is WordListItem.WordItem
                        && oldItem.word == newItem.word

                return isSameSearchHistory || isSameWord
            }

            override fun areContentsTheSame(oldItem: WordListItem, newItem: WordListItem): Boolean =
                oldItem == newItem
        }
    }
}

sealed class WordListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    class SearchHistoryViewHolder(
        private val binding: ItemSearchHistoryBinding,
        onClickItem: () -> Unit
    ) : WordListViewHolder(binding.root) {

        init {
            binding.root.setOnClickListenerThrottled {
                onClickItem.invoke()
            }
        }

        fun bind(item: WordListItem.SearchHistory) {
            binding.executeAfter {
                this.count = item.count
            }
        }
    }

    class WordItemViewHolder(
        private val binding: ItemWordBinding
    ) : WordListViewHolder(binding.root) {

        fun bind(
            item: WordListItem.WordItem,
            onLongClickWord: (Word) -> Boolean,
            onClickLevel: (Word) -> Unit
        ) {
            binding.tvWord.setOnLongClickListener {
                onLongClickWord.invoke(item.word)
            }

            binding.btnLevel.setOnClickLevelListener {
                item.word.level = item.word.level.next()
                onClickLevel.invoke(item.word)
            }

            binding.executeAfter {
                this.data = item.word
            }
        }
    }
}

sealed class WordListItem {
    data class SearchHistory(val count: Int) : WordListItem()

    data class WordItem(val word: Word) : WordListItem()
}