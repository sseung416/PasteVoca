package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemWordCardBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.Word

class WordCardStackAdapter : RecyclerView.Adapter<WordCardStackAdapter.ViewHolder>() {
    private val list = arrayListOf<Word>()

    inner class ViewHolder(
        private val binding: ItemWordCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.tvCount.text = "${position + 1}/${list.size}"
            binding.data = list[position]
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemWordCardBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<Word>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}