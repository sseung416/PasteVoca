package kr.co.dgsw.pastvoca.widget.recyclerview.decoration

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

class CustomDecoration(
    private val height: Float,
    @ColorInt private val color: Int
) : RecyclerView.ItemDecoration() {
    private val paint = Paint()

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        paint.color = color

        val start = parent.paddingStart.toFloat()
        val end = parent.paddingEnd.toFloat()

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = (child.bottom + params.bottomMargin).toFloat()
            val bottom = top + height

            c.drawRect(start, top, end, bottom, paint)
        }
    }
}