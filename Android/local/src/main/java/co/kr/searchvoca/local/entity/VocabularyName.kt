package co.kr.searchvoca.local.entity

import java.io.Serializable

data class VocabularyName(
    val id: Int?,
    val name: String? = null
) : Serializable