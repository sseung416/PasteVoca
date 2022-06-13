package co.kr.dgsw.searchvoca.datasource.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "correctionsWord",
    foreignKeys = [ForeignKey(
        entity = Vocabulary::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("voca_id")
    )]
)
data class CorrectionsWord(
    val word: String,
    val meaning: String,
    @ColumnInfo(name = "voca_id") var vocabularyId: Int,
    var isCorrect: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
