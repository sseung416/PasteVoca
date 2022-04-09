package co.kr.dgsw.searchvoca.widget.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemCorrectionsViewBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.Word

class CorrectionsAdapter : RecyclerView.Adapter<CorrectionsAdapter.ViewHolder>() {
    private val allList = arrayListOf<Word>()
    private val correctList = arrayListOf<Word>()
    private val wrongList = arrayListOf<Word>()

    inner class ViewHolder(
        private val binding: ItemCorrectionsViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(list: List<Word>) {
            binding.rvCorrectionView.adapter = WordAdapter().apply {
                setList(list)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        with (ItemCorrectionsViewBinding.inflate(LayoutInflater.from(parent.context))) {
            root.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            ViewHolder(this)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = when (position) {
            0 -> wrongList
            1 -> correctList
            2 -> allList
            else -> null
        }
        holder.bind(list!!)
    }

    override fun getItemCount(): Int = 3

    fun setList(triple: Triple<List<Word>, List<Word>, List<Word>>) {
        allList.addAll(triple.first)
        wrongList.addAll(triple.second)
        correctList.addAll(triple.third)
    }
}