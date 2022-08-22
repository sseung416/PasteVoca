package co.kr.searchvoca.remote.response

import co.kr.searchvoca.data.model.SearchResult

data class DictionaryResponse(
    val channel: Channel
)

data class Channel(
    val description: String,
    val item: List<Item>,
    val lastbuilddate: String,
    val link: String,
    val num: Int,
    val start: Int,
    val title: String,
    val total: Int
)

data class Item(
    val pos: String,
    val sense: Sense,
    val sup_no: Int,
    val target_code: Int,
    val word: String
)

data class Sense(
    val definition: String,
    val link: String,
    val type: String
)

fun DictionaryResponse.toModel() =
    channel.item.map {
        SearchResult(it.sense.definition)
    }
