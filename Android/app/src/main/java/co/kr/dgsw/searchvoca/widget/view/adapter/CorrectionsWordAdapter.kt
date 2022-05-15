package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemCorrectionsWordBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.CorrectionsWord

class CorrectionsWordAdapter(
    private val list: List<CorrectionsWord>
) : RecyclerView.Adapter<CorrectionsWordAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: ItemCorrectionsWordBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CorrectionsWord) {
            binding.data = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemCorrectionsWordBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}