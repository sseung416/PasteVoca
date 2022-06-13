package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemCorrectionsTitleBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.widget.extension.setOnClickListenerThrottled

class CorrectionsListAdapter : RecyclerView.Adapter<CorrectionsListAdapter.ViewHolder>() {
    private val list = arrayListOf<Vocabulary>()

    var onClickItemListener: ((Vocabulary)->Unit)? = null
    var onClickDeleteListener: ((Vocabulary)->Unit)? = null

    inner class ViewHolder(
        private val binding: ItemCorrectionsTitleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Vocabulary) {
            binding.data = data
            binding.executePendingBindings()

            binding.layoutCorrections.setOnClickListenerThrottled {
                onClickItemListener?.invoke(data)
            }

            binding.btnDelete.setOnClickListenerThrottled {
                onClickDeleteListener?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        with (ItemCorrectionsTitleBinding.inflate(LayoutInflater.from(parent.context))) {
            root.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            ViewHolder(this)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setList(list: List<Vocabulary>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}