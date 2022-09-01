package co.kr.searchvoca.widget.view.decoration

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

class CustomDecoration(
    private val height: Float,
    @ColorInt private val color: Int,
    private val padding: Float
) : RecyclerView.ItemDecoration() {
    private val paint = Paint()

    init {
        paint.color = color
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val start = parent.paddingStart.toFloat() + padding
        val end = parent.width - parent.paddingEnd.toFloat() - padding

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = (child.bottom + params.bottomMargin).toFloat()
            val bottom = top + height

            c.drawRect(start, top, end, bottom, paint)
        }
    }
}