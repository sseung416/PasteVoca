package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemSpinnerVocabularyBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName

class BottomSheetAdapter(
    private val list: List<VocabularyName>
) : RecyclerView.Adapter<BottomSheetAdapter.ViewHolder>() {
    var onClickItemListener: ((Int?) -> Unit)? = null

    inner class ViewHolder(
        private val binding: ItemSpinnerVocabularyBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VocabularyName) {
            binding.tvVoca.text = item.name

            binding.layoutSpinner.setOnClickListener {
                onClickItemListener?.invoke(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        with(ItemSpinnerVocabularyBinding.inflate(LayoutInflater.from(parent.context))) {
            root.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            ViewHolder(this)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}