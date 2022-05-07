package co.kr.dgsw.searchvoca.datasource.model.dao

import androidx.room.Dao
import androidx.room.Query
import co.kr.dgsw.searchvoca.base.BaseDao
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName

@Dao
interface VocabularyDao : BaseDao<Vocabulary> {
    @Query("SELECT * FROM vocabulary WHERE id <> ${Vocabulary.VOCABULARY_ID_SEARCH}")
    suspend fun getVocabularies(): List<Vocabulary>

    @Query("SELECT id, name FROM vocabulary WHERE id <> ${Vocabulary.VOCABULARY_ID_SEARCH}")
    suspend fun getVocabularyNames(): List<VocabularyName>

    @Query("SELECT id, name FROM vocabulary WHERE id = :id")
    suspend fun getVocabularyNameById(id: Int): VocabularyName
}