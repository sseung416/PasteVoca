package co.kr.dgsw.searchvoca.datasource.model.dao

import androidx.room.Dao
import androidx.room.Query
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName

@Dao
interface VocabularyDao : BaseDao<Vocabulary> {
    @Query("SELECT * FROM vocabulary WHERE id <> ${Vocabulary.VOCABULARY_ID_SEARCH} AND isCorrections = true")
    suspend fun getVocabularies(): List<Vocabulary>

    @Query("SELECT * FROM vocabulary WHERE isCorrections = 1")
    suspend fun getCorrectionsVocabularies(): List<Vocabulary>

    @Query("SELECT COUNT(*) FROM vocabulary WHERE isCorrections = 1")
    suspend fun getLastCorrectionsVocabularyId(): Int

    @Query("SELECT id, name FROM vocabulary WHERE id <> ${Vocabulary.VOCABULARY_ID_SEARCH} AND isCorrections = false")
    suspend fun getVocabularyNames(): List<VocabularyName>

    @Query("SELECT id, name FROM vocabulary WHERE id = :id")
    suspend fun getVocabularyNameById(id: Int): VocabularyName
}