package co.kr.dgsw.searchvoca.ui.home

import co.kr.dgsw.searchvoca.ui.dialog.DefaultBottomSheetDialog

class WordFilterDialog(
    itemClickEvent: (Pair<Int?, String>) -> Unit
) : DefaultBottomSheetDialog(
    listOf(SortType.SHUFFLE.toPair(),
        SortType.LEVEL_EASY.toPair(),
        SortType.LEVEL_DIFFICULT.toPair()
    ), itemClickEvent)