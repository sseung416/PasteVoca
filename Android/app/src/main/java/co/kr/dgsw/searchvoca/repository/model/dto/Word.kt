package co.kr.dgsw.searchvoca.repository.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "word",
    foreignKeys = [ForeignKey(
        entity = Vocabulary::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("voca_id")
    )]
)
data class Word(
    @ColumnInfo(name = "voca_id") val vocabularyId: Int,
    val word: String,
    val meaning: String,
    var type: Int = EASY,
    @PrimaryKey(autoGenerate = true) var id: Int? = null
) : Serializable {
    var isCorrect = true

    companion object {
        const val DIFFICULT = 1
        const val MIDDLE = 2
        const val EASY = 3
    }
}
