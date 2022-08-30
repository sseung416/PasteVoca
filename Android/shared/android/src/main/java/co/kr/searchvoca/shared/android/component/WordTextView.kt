package co.kr.searchvoca.shared.android.component

import android.content.Context
import android.util.AttributeSet
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import co.kr.searchvoca.shared.android.R

class WordTextView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatTextView(context, attrs, defStyle) {

    var word: String = ""
        set(value) {
            field = value
            text = value
        }
    var definition: String? = null

    private var wordVisible = true

    init {
        this.setOnClickListener {
            text = if (wordVisible) word else definition
            wordVisible = wordVisible.not()
        }

        this.setOnLongClickListener {
            Toast.makeText(context, "안여", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }

        setupTyped()
    }

    private fun setupTyped() {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.WordTextView)

        word = typedArray.getString(R.styleable.WordTextView_word).toString()
        definition = typedArray.getString(R.styleable.WordTextView_definition).toString()

        typedArray.recycle()
    }
}