package co.kr.searchvoca.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import co.kr.searchvoca.data.model.WordResult
import java.io.Serializable
import co.kr.searchvoca.shared.domain.Level

@Entity(
    tableName = "word",
    foreignKeys = [ForeignKey(
        entity = VocabularyEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("voca_id")
    )]
)
data class WordEntity(
    @ColumnInfo(name = "voca_id") val vocabularyId: Int,
    val word: String,
    val definition: String,
    var level: Int = Level.EASY.ordinal,
    @PrimaryKey(autoGenerate = true) var id: Int? = null
) : Serializable

fun WordEntity.toModel() =
    WordResult(
        vocabularyId,
        word,
        definition,
        level,
        id
    )

fun WordResult.toEntity() =
    WordEntity(
        vocabularyId,
        word,
        definition,
        level,
        id
    )