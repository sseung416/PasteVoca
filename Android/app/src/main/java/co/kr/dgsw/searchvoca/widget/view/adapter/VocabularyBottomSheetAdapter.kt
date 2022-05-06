package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemTextBinding
import co.kr.dgsw.searchvoca.databinding.ItemVocabularyAddBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName

class VocabularyBottomSheetAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list = arrayListOf<VocabularyName>()

    var onClickItemListener: ((VocabularyName) -> Unit)? = null
    var onClickAddVocabulary: (()->Unit)? = null

    inner class TextViewHolder(
        private val binding: ItemTextBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VocabularyName) {
            binding.tv.text = item.name

            binding.layout.setOnClickListener {
                onClickItemListener?.invoke(item)
            }
        }
    }

    inner class AddViewHolder(
        private val binding: ItemVocabularyAddBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.linear.setOnClickListener {
                onClickAddVocabulary?.invoke()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            VOCABULARY_ADD -> AddViewHolder(ItemVocabularyAddBinding.inflate(LayoutInflater.from(parent.context)))

            else -> {
                with(ItemTextBinding.inflate(LayoutInflater.from(parent.context))) {
                    root.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                    TextViewHolder(this)
                }
            }
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TextViewHolder)
            holder.bind(list[position])
        else if (holder is AddViewHolder)
            holder.bind()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.lastIndex) VOCABULARY_ADD else super.getItemViewType(position)
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<VocabularyName>) {
        this.list.clear()
        this.list.addAll(list)
        this.list.add(VocabularyName(null))
        notifyDataSetChanged()
    }

    companion object {
        private const val VOCABULARY_ADD = 1
    }
}