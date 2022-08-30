package co.kr.dgsw.searchvoca.ui.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemTextBinding

class DefaultBottomSheetAdapter(
    private val list: List<Pair<Int?, String>>,
    private val onClickItem: ((Pair<Int?, String>) -> Unit)?
) : RecyclerView.Adapter<DefaultBottomSheetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultBottomSheetViewHolder =
        with (ItemTextBinding.inflate(LayoutInflater.from(parent.context))) {
            root.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            DefaultBottomSheetViewHolder(this, onClickItem)
        }

    override fun onBindViewHolder(holder: DefaultBottomSheetViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

class DefaultBottomSheetViewHolder(
    private val binding: ItemTextBinding,
    private val onClickItem: ((Pair<Int?, String>) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Pair<Int?, String>) = binding.apply {
        tv.text = data.second
        root.setOnClickListener {
            onClickItem?.invoke(data)
        }
    }
}