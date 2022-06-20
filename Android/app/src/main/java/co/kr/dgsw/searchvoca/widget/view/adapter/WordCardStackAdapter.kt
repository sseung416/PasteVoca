package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemWordCardBinding
import co.kr.dgsw.searchvoca.view.data.TestWord

class WordCardStackAdapter : RecyclerView.Adapter<WordCardStackAdapter.ViewHolder>() {
    private val corrections = arrayListOf<TestWord>()
    private var adapterPosition = -1

    inner class ViewHolder(
        private val binding: ItemWordCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        // 현재 단어뜻이 나와있는지 단어가 나와있는지 확인하는 값
        private var isShowMeaning = false

        fun bind(position: Int) {
            val data = corrections[position]

            binding.cvCard.setOnClickListener {
                binding.tvWordCard.text = if (isShowMeaning) data.word else data.meaning
                isShowMeaning = !isShowMeaning
            }

            binding.tvCountCard.text = "${position + 1}/${itemCount}"
            binding.data = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemWordCardBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = corrections.size

    fun setList(list: List<TestWord>) {
        this.corrections.clear()
        this.corrections.addAll(list)
        notifyDataSetChanged()
    }

    fun getList() = corrections

    fun setCorrect(boolean: Boolean) {
        corrections[getAdapterPosition()].isCorrect = boolean
    }

    private fun getAdapterPosition() = ++adapterPosition
}