package co.kr.dgsw.searchvoca.ui.home

fun MutableList<WordListItem>.itemShuffle(): List<WordListItem> =
    this.removeSearchHistory()
        .listShuffle()
        .addSearchHistory()

fun MutableList<WordListItem>.sortByLevelDifficult(): List<WordListItem> =
    this.removeSearchHistory()
        .listSortWith(compareBy {
            (it as WordListItem.WordItem).word.level.ordinal
        })
        .addSearchHistory()

fun MutableList<WordListItem>.sortByLevelEasy(): List<WordListItem> =
    this.removeSearchHistory()
        .listSortWith(compareBy {
            (it as WordListItem.WordItem).word.level.ordinal
        })
        .addSearchHistory()

private var searchHistory: WordListItem.SearchHistory? = null

private fun MutableList<WordListItem>.removeSearchHistory(): MutableList<WordListItem> {
    if (this.hasSearchHistory()) {
        searchHistory = this.removeAt(0) as WordListItem.SearchHistory
    }
    return this
}

private fun MutableList<WordListItem>.addSearchHistory(): MutableList<WordListItem> {
    searchHistory?.let {
        this.add(it)
        searchHistory = null
    }
    return this
}

private fun MutableList<WordListItem>.listSortWith(
    comparator: Comparator<WordListItem>
): MutableList<WordListItem> =
    this.apply { sortWith(comparator) }

private fun MutableList<WordListItem>.listShuffle(): MutableList<WordListItem> =
    this.apply { shuffle() }

private fun List<WordListItem>.hasSearchHistory(): Boolean = this[0] is WordListItem.SearchHistory