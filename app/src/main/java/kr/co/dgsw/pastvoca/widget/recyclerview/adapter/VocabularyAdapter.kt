package kr.co.dgsw.pastvoca.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.dgsw.pastvoca.databinding.LayoutVocabularyBinding
import kr.co.dgsw.pastvoca.repository.model.dto.VocabularyName

class VocabularyAdapter : RecyclerView.Adapter<VocabularyAdapter.ViewHolder>() {
    private val list = arrayListOf<VocabularyName>()

    private var selectedPosition = 0

    inner class ViewHolder(
        private val binding: LayoutVocabularyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.radioButton.apply {
                text = list[position].name

                setOnClickListener {
                    binding.radioButton.isSelected = true
                    notifyItemChanged(selectedPosition)
                    selectedPosition = position
                }

                isSelected = false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutVocabularyBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<VocabularyName>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}