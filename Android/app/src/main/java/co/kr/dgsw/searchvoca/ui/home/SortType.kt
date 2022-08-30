package co.kr.dgsw.searchvoca.ui.home

enum class SortType(val title: String) {
    SHUFFLE("랜덤"),
    LEVEL_DIFFICULT("어려운순으로"),
    LEVEL_EASY("쉬운순으로")
}

fun SortType.toPair() = Pair(this.ordinal, this.title)

fun Pair<Int?, String>.toSortType() = SortType.values()[this.first!!]