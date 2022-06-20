package co.kr.dgsw.searchvoca.view.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// todo move package
@Parcelize
data class TestWord(
    val word: String,
    val meaning: String,
    var isCorrect: Boolean = false,
) : Parcelable










