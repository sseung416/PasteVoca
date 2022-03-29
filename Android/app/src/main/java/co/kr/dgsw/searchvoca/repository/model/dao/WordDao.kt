package co.kr.dgsw.searchvoca.repository.model.dao

import androidx.room.Dao
import androidx.room.Query
import co.kr.dgsw.searchvoca.base.BaseDao
import co.kr.dgsw.searchvoca.repository.model.dto.Word

@Dao
interface WordDao : BaseDao<Word> {
    @Query("SELECT * FROM word")
    suspend fun getAllWords(): List<Word>

    @Query("SELECT * FROM word WHERE voca_id = :vocabularyId")
    suspend fun getWordsByVocabulary(vocabularyId: Int): List<Word>
}