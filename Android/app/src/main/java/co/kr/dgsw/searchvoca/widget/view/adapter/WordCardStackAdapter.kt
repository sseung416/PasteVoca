package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.LayoutWordCardBinding
import co.kr.dgsw.searchvoca.repository.model.dto.Word

class WordCardStackAdapter : RecyclerView.Adapter<WordCardStackAdapter.ViewHolder>() {
    private val list = arrayListOf<Word>()

    class ViewHolder(
        private val binding: LayoutWordCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word) {
            binding.data = word
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        with (LayoutWordCardBinding.inflate(LayoutInflater.from(parent.context))) {
            root.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
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