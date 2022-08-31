package co.kr.dgsw.searchvoca.ui.quiz

import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import co.kr.searchvoca.shared.android.component.QuizCardView


@BindingAdapter(
    value = ["title", "icon"],
    requireAll = true
)
fun QuizCardView.bindQuizCardViewData(title: String, @DrawableRes icon: Int) {
    this.title = title
    this.iconRes = icon
}