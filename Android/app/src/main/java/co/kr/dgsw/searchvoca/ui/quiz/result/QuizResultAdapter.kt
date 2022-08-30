package co.kr.dgsw.searchvoca.ui.quiz.result

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.databinding.ItemQuizResultBinding
import co.kr.dgsw.searchvoca.ui.quiz.TestWord

class QuizResultAdapter(
    private val list: List<TestWord>
) : RecyclerView.Adapter<QuizResultAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ItemQuizResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TestWord) {
            binding.data = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        with (ItemQuizResultBinding.inflate(LayoutInflater.from(parent.context))) {
            root.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            ViewHolder(this)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}