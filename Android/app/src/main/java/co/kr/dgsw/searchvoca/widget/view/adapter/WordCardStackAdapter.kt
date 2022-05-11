package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemWordCardBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.Word

class WordCardStackAdapter : RecyclerView.Adapter<WordCardStackAdapter.ViewHolder>() {
    private val list = arrayListOf<Word>()
    private var adapterPosition = -1

    inner class ViewHolder(
        private val binding: ItemWordCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        // 현재 단어뜻이 나와있는지 단어가 나와있는지 확인하는 값
        private var isShowMeaning = false

        fun bind(position: Int) {
            val data = list[position]

            binding.cvCard.setOnClickListener {
                binding.tvWordCard.text = if (isShowMeaning) data.word else data.meaning
                isShowMeaning = !isShowMeaning
            }

            binding.tvCountCard.text = "${position + 1}/${list.size}"
            binding.data = data
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

    fun getList() = list

    fun setCorrect(boolean: Boolean) {
        list[getAdapterPosition()].isCorrect = boolean
    }

    private fun getAdapterPosition() = ++adapterPosition
}