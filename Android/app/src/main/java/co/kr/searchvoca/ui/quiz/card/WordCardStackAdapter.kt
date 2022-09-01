package co.kr.searchvoca.ui.quiz.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.searchvoca.databinding.ItemWordCardBinding
import co.kr.searchvoca.ui.quiz.TestWord
import co.kr.searchvoca.ui.executeAfter

class WordCardStackAdapter(
    private val list: List<TestWord>
) : RecyclerView.Adapter<WordCardStackAdapter.ViewHolder>() {

    private var adapterPosition = -1

    inner class ViewHolder(
        private val binding: ItemWordCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val data = list[position]
            binding.executeAfter {
                this.data = data
                this.currentPosition = position + 1
                this.size = itemCount
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemWordCardBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = list.size

    fun getList() = list

    fun setCorrect(boolean: Boolean) {
        list[getAdapterPosition()].isCorrect = boolean
    }

    private fun getAdapterPosition() = ++adapterPosition
}