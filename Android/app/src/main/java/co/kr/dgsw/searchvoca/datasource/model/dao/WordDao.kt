package co.kr.dgsw.searchvoca.datasource.model.dao

import androidx.room.Dao
import androidx.room.Query
import co.kr.dgsw.searchvoca.base.BaseDao
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.datasource.model.dto.Word

@Dao
interface WordDao : BaseDao<Word> {
    @Query("SELECT * FROM word WHERE voca_id <> ${Vocabulary.VOCABULARY_ID_SEARCH}")
    suspend fun getAllWords(): List<Word>

    @Query("SELECT * FROM word WHERE voca_id = :vocabularyId")
    suspend fun getWordsByVocabulary(vocabularyId: Int): List<Word>

    @Query("UPDATE word SET voca_id = :vocabularyId WHERE id IN(:ids)")
    suspend fun updateWordVocabulary(ids: List<Int>, vocabularyId: Int)

    @Query("DELETE FROM word WHERE id IN (:ids)")
    suspend fun delete(ids: List<Int>)
}