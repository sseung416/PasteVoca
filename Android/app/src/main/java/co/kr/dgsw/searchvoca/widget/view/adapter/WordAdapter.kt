package co.kr.dgsw.searchvoca.widget.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.databinding.ItemWordBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.widget.extension.setOnClickListenerThrottled

class WordAdapter : RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    private val list = arrayListOf<Word>()
    var onLongClickWordListener: ((Word) -> Boolean)? = null
    var onClickTypeListener: ((Word) -> Unit)? = null
    var onClickSoundListener: ((String) -> Unit)? = null

    inner class ViewHolder(
        private val binding: ItemWordBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Word) {
            init(item, binding.root.context)

            binding.tvWord.setOnClickListener {
                binding.tvMeaning.apply {
                    visibility = if (isGone) VISIBLE else GONE
                }
            }

            binding.tvWord.setOnLongClickListener {
                onLongClickWordListener?.invoke(item)!!
            }

            var selectedType = Word.EASY
            binding.btnType.setOnClickListener {
                val (type, resource, color) = when (selectedType) {
                    Word.EASY -> getMiddleTypeData()
                    Word.MIDDLE -> getDifficultTypeData()
                    Word.DIFFICULT -> getEasyTypeData()
                    else -> getEasyTypeData()
                }

                selectedType = type
                item.type = selectedType

                setDifficultyView(it.context, resource, color)
                onClickTypeListener?.invoke(item)
            }

            binding.btnSound.setOnClickListenerThrottled {
                onClickSoundListener?.invoke(item.word)
            }
        }

        private fun init(item: Word, context: Context) {
            val (_, resource, color) = when (item.type) {
                Word.EASY -> getEasyTypeData()
                Word.MIDDLE -> getMiddleTypeData()
                Word.DIFFICULT -> getDifficultTypeData()
                else -> getEasyTypeData()
            }

            setDifficultyView(context, resource, color)
            binding.data = item
            binding.executePendingBindings()
        }

        private fun setDifficultyView(context: Context, resource: Int, color: Int) {
            binding.btnType.setImageResource(resource)
            binding.viewDifficulty.setBackgroundColor(ContextCompat.getColor(context, color))
        }

        private fun getEasyTypeData() =
            Triple(Word.EASY, R.drawable.ic_type_easy, android.R.color.transparent)

        private fun getMiddleTypeData() =
            Triple(Word.MIDDLE, R.drawable.ic_type_middle, R.color.type_middle)

        private fun getDifficultTypeData() =
            Triple(Word.DIFFICULT, R.drawable.ic_type_difficult, R.color.type_difficult)
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

    fun sort(sortType: Int): List<Word> {
        when (sortType) {
            SHUFFLE -> list.shuffle()
            SORT_DIFFICULT -> list.sortWith(compareBy { it.type })
            SORT_EASY -> list.sortWith(compareByDescending { it.type })
        }
        notifyDataSetChanged()
        return list
    }

    companion object {
        const val SHUFFLE = 0
        const val SORT_DIFFICULT = -1
        const val SORT_EASY = -2
    }
}