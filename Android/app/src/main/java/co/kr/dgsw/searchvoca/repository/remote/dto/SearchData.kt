package co.kr.dgsw.searchvoca.repository.remote.dto

data class SearchData(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<SearchItem>
)

data class SearchItem(
    val title: String,
    val link: String,
    val description: String,
    val thumbnail: String
)