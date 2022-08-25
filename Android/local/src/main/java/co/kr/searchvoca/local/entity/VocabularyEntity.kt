package co.kr.searchvoca.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import co.kr.searchvoca.data.model.VocabularyResult

@Entity(tableName = "vocabulary")
data class VocabularyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    val explanation: String? = null,
)

fun VocabularyEntity.toModel() =
    VocabularyResult(
        name,
        id,
        explanation
    )

fun VocabularyResult.toEntity() =
    VocabularyEntity(
        id,
        name,
        explanation
    )
