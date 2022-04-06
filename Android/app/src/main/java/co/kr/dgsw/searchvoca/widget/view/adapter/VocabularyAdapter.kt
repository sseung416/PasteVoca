package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.LayoutVocabularyBinding
import co.kr.dgsw.searchvoca.repository.model.dto.VocabularyName

class VocabularyAdapter(
    private var selectedPosition: Int
) : RecyclerView.Adapter<VocabularyAdapter.ViewHolder>() {
    private val list = arrayListOf<VocabularyName>()
    private var isInit = false

    inner class ViewHolder(
        private val binding: LayoutVocabularyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.radioButton.apply {
                text = list[position].name

                setOnClickListener {
                    if (selectedPosition != position) {
                        binding.radioButton.isChecked = true
                        notifyItemChanged(selectedPosition)
                        selectedPosition = position
                    }
                }

                if (!isInit && position == selectedPosition) initFirstItem() else isChecked = false
            }
        }

        private fun initFirstItem() {
            binding.radioButton.isChecked = true
            isInit = true
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

    fun getSelectedVocabulary() = list[selectedPosition]
}