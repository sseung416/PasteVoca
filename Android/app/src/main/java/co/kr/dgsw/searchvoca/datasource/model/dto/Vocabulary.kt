package co.kr.dgsw.searchvoca.datasource.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vocabulary")
data class Vocabulary(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val explanation: String? = null,
    val language: String? = null
)
