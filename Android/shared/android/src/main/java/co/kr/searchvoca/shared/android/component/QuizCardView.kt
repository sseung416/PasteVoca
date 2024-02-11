package co.kr.searchvoca.shared.android.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import co.kr.searchvoca.shared.android.R
import co.kr.searchvoca.shared.android.useWith

/**
 * QuizFragment 에서 사용되는 퀴즈 선택 view
 * */
class QuizCardView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private val ivIcon: ImageView
    private val tvTitle: TextView

    @DrawableRes
    var iconRes = R.drawable.ic_card
        set(value) {
            field = value
            ivIcon.setImageResource(value)
        }

    var title = ""
        set(value) {
            field = value
            tvTitle.text = value
        }

    init {
        inflate(context, R.layout.view_quiz_card, this)

        ivIcon = findViewById(R.id.iv_icon)
        tvTitle = findViewById(R.id.tv_title)

        setupTyped()
    }

    @SuppressLint("Recycle")
    private fun setupTyped() {
        context.obtainStyledAttributes(attrs, R.styleable.QuizCardView).useWith {
            title = getString(R.styleable.QuizCardView_title).toString()
            iconRes = getInteger(R.styleable.QuizCardView_cardIcon, R.drawable.ic_card)
        }
    }
}