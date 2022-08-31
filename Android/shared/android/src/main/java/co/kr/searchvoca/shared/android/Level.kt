package co.kr.searchvoca.shared.android

/**
 * 단어별 어려움을 구분
 * */
enum class Level(val drawableRes: Int, val color: Int) {
    EASY(R.drawable.ic_level_easy, R.color.level_easy),

    MEDIUM(R.drawable.ic_level_middle, R.color.level_middle),

    DIFFICULT(R.drawable.ic_level_difficult, R.color.level_difficult)
}