package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemVocabularyTextBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName

class VocabularySpinnerAdapter(
    private val list: List<VocabularyName>
) : RecyclerView.Adapter<VocabularySpinnerAdapter.ViewHolder>() {
    var onClickItem: ((Int) -> Unit)? = null

    inner class ViewHolder(
        private val binding: ItemVocabularyTextBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VocabularyName) {
            binding.tvVoca.apply {
                text = item.name
                setOnClickListener {
                    onClickItem?.invoke(item.id!!)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemVocabularyTextBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}