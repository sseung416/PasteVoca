package co.kr.dgsw.searchvoca.datasource.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vocabulary")
data class Vocabulary(
    val name: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val explanation: String? = null,
    val language: String? = null
) {
    companion object {
        const val VOCABULARY_ID_SEARCH = 1
        const val VOCABULARY_ID_NO_NAMED = 2
    }
}
