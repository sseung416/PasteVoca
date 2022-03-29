package co.kr.dgsw.searchvoca.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.LayoutWordBinding
import co.kr.dgsw.searchvoca.repository.model.dto.Word

class WordAdapter : RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    private val list = arrayListOf<Word>()

    inner class ViewHolder(
        private val binding: LayoutWordBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Word) {
            binding.data = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        with(LayoutWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)) {
            this.root.layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            ViewHolder(this)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<Word>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}