package kr.co.dgsw.pastvoca.repository.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "word",
    foreignKeys = [ForeignKey(
        entity = Vocabulary::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("voca_id")
    )]
)
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "voca_id") val vocabularyId: Int,
    val word: String,
    val meaning: String,
    val type: Int
)
