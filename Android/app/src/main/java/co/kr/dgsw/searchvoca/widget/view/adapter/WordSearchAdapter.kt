package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemWordSearchBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.Word

class WordSearchAdapter : RecyclerView.Adapter<WordSearchAdapter.ViewHolder>() {
    private val list = arrayListOf<SearchWord>()

    inner class ViewHolder(
        private val binding: ItemWordSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Word, position: Int) = binding.apply {
            tvWord.text = item.word
            tvMeaning.text = item.meaning
            layoutWord.setOnClickListener {
                setCheckedButton(position)
            }
        }

        fun setCheckedButton(position: Int) {
            binding.radio.apply {
                list[position].isChecked = !isChecked
                isChecked = !isChecked
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemWordSearchBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position].word, position)
    }

    override fun getItemCount(): Int = list.size

    fun getCheckedWordId() = list.filter { it.isChecked }.map { it.word.id!! }

    fun setList(list: List<Word>) {
        this.list.clear()
        this.list.addAll(list.map { SearchWord(it) })
        notifyDataSetChanged()
    }

    fun removeCheckedItems() {
        list.removeIf { it.isChecked }
        notifyDataSetChanged()
    }
}

data class SearchWord(val word: Word, var isChecked: Boolean = false)