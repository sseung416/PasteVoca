package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemWordBinding
import co.kr.dgsw.searchvoca.repository.model.dto.Word

class WordAdapter : RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    private val list = arrayListOf<Word>()
    var onLongClickWordListener: ((Word) -> Boolean)? = null

    inner class ViewHolder(
        private val binding: ItemWordBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Word) {
            binding.tvWord.setOnClickListener {
                binding.tvMeaning.apply {
                    visibility = if (isInvisible) VISIBLE else INVISIBLE
                }
            }

            binding.tvWord.setOnLongClickListener {
                onLongClickWordListener?.invoke(item)!!
            }

            binding.data = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        with(ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)) {
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