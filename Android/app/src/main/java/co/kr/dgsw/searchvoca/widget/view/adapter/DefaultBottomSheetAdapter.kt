package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemTextBinding

class DefaultBottomSheetAdapter(
    private val list: List<Pair<Int?, String>>
) : RecyclerView.Adapter<DefaultBottomSheetAdapter.ViewHolder>() {
    var onClickItem: ((Pair<Int?, String>) -> Unit)? = null

    inner class ViewHolder(
        private val binding: ItemTextBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Pair<Int?, String>) = binding.apply {
            tv.text = data.second
            layout.setOnClickListener {
                onClickItem?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        with (ItemTextBinding.inflate(LayoutInflater.from(parent.context))) {
            root.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            ViewHolder(this)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}