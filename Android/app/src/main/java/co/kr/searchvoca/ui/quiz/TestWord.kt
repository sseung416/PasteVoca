package co.kr.searchvoca.ui.quiz

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TestWord(
    val word: String,
    val definition: String,
    var isCorrect: Boolean = false,
) : Parcelable