package co.kr.searchvoca.shared.android.component

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import co.kr.searchvoca.shared.android.Level
import co.kr.searchvoca.shared.android.R
import co.kr.searchvoca.shared.android.next

class LevelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatImageButton(context, attrs, defStyle) {

    var level = Level.EASY
        set(value) {
            field = value
            setImageResource(level.drawableRes)
        }

    init {
        setImageResource(Level.EASY.drawableRes)
        setOnClickLevelListener(null)

        setPadding()
    }

    fun setOnClickLevelListener(listener: OnClickLevelListener?) {
        this.setOnClickListener {
            level = level.next()
            listener?.onClick()
        }
    }

    // enum 의 이름으로 level 설정
    fun setLevelByName(name: String) {
        level = Level.valueOf(name)
    }

    private fun setPadding() {
        val padding = context.resources.getDimensionPixelSize(R.dimen.margin_large)
        setPadding(padding, padding, padding, padding)
    }
}

fun interface OnClickLevelListener {
    fun onClick()
}