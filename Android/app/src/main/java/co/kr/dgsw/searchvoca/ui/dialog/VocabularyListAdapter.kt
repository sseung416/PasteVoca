package co.kr.dgsw.searchvoca.ui.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemTextBinding
import co.kr.dgsw.searchvoca.databinding.ItemVocabularyAddBinding
import co.kr.searchvoca.domain.model.Vocabulary
import co.kr.searchvoca.domain.model.default

class VocabularyListAdapter(
    private val onClickItemListener: ((Vocabulary) -> Unit),
    private val onClickAddListener: (() -> Unit)?,
) : ListAdapter<VocabularyListItem, VocabularyListViewHolder>(diffCallback) {

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is VocabularyListItem.VocabularyItem -> 0
            is VocabularyListItem.AddVocabulary -> 1
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabularyListViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            0 ->
                VocabularyListViewHolder.TextViewHolder(
                    ItemTextBinding.inflate(inflater),
                    onClickItemListener
                )

            else ->
                VocabularyListViewHolder.AddViewHolder(
                    ItemVocabularyAddBinding.inflate(inflater),
                    onClickAddListener
                )
        }
    }

    override fun onBindViewHolder(holder: VocabularyListViewHolder, position: Int) {
        if (holder is VocabularyListViewHolder.TextViewHolder)
            holder.bind(getItem(position) as VocabularyListItem.VocabularyItem)
    }

    override fun getItemCount(): Int = currentList.size

    override fun submitList(list: MutableList<VocabularyListItem>?) {
        if (onClickAddListener != null)
            list?.add(VocabularyListItem.AddVocabulary)

        super.submitList(list)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<VocabularyListItem>() {
            override fun areItemsTheSame(
                oldItem: VocabularyListItem,
                newItem: VocabularyListItem
            ): Boolean =
                oldItem is VocabularyListItem.VocabularyItem
                        && newItem is VocabularyListItem.VocabularyItem
                        && oldItem.vocabulary == newItem.vocabulary

            override fun areContentsTheSame(
                oldItem: VocabularyListItem,
                newItem: VocabularyListItem
            ): Boolean =
                oldItem == newItem
        }
    }
}

sealed class VocabularyListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    class TextViewHolder(
        private val binding: ItemTextBinding,
        private val onItemClickListener: (Vocabulary) -> Unit
    ) : VocabularyListViewHolder(binding.root) {

        fun bind(item: VocabularyListItem.VocabularyItem) {
            binding.tv.text = item.vocabulary.name
            binding.root.setOnClickListener {
                onItemClickListener.invoke(item.vocabulary)
            }
        }
    }

    class AddViewHolder(
        binding: ItemVocabularyAddBinding,
        onItemClickListener: (() -> Unit)?
    ) : VocabularyListViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke()
            }
        }
    }
}

sealed class VocabularyListItem {

    data class VocabularyItem(val vocabulary: Vocabulary) : VocabularyListItem()

    object AddVocabulary : VocabularyListItem()
}