package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemSearchResultBinding

class SearchWordAdapter(
    private val list: List<String>
) : RecyclerView.Adapter<SearchWordAdapter.ViewHolder>() {
    var onClickMeaningListener: ((String) -> Unit)? = null

    inner class ViewHolder(
        private val binding: ItemSearchResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meaning: String) {
            binding.tvMeaning.apply {
                text = meaning
                setOnClickListener {
                    onClickMeaningListener?.invoke(text.toString())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}