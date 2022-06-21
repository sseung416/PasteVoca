package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemCorrectionsWordBinding
import co.kr.dgsw.searchvoca.view.data.TestWord

class CorrectionsWordAdapter(
    private val list: List<TestWord>
) : RecyclerView.Adapter<CorrectionsWordAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: ItemCorrectionsWordBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TestWord) {
            binding.data = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        with (ItemCorrectionsWordBinding.inflate(LayoutInflater.from(parent.context))) {
            root.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            ViewHolder(this)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}