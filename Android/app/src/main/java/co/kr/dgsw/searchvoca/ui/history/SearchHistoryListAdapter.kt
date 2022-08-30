package co.kr.dgsw.searchvoca.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemWordSearchBinding
import co.kr.dgsw.searchvoca.ui.executeAfter
import co.kr.searchvoca.domain.model.Word

class SearchHistoryListAdapter : ListAdapter<History, SearchHistoryViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder =
        SearchHistoryViewHolder(ItemWordSearchBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun getCheckedItemIds() = currentList.filter { (it as History).cheked }.map { it.word.id!! }

    fun removeCheckedItems() {
        currentList.removeIf { it.cheked }
        notifyDataSetChanged()
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean =
                oldItem.word.id == newItem.word.id

            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean =
                oldItem == newItem
        }
    }
}

class SearchHistoryViewHolder(
    private val binding: ItemWordSearchBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: History) {
        binding.executeAfter {
            word = item.word
//            checked = item.cheked
        }
    }

    fun select() {
        binding.executeAfter {
//            checked = checked.not()
        }
    }
}

data class History(val word: Word, var cheked: Boolean = false)