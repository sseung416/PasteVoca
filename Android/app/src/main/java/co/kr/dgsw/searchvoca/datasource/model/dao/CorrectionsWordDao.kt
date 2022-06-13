package co.kr.dgsw.searchvoca.datasource.model.dao

import androidx.room.Dao
import androidx.room.Query
import co.kr.dgsw.searchvoca.datasource.model.dto.CorrectionsWord

@Dao
interface CorrectionsWordDao : BaseDao<CorrectionsWord> {
    @Query("SELECT * FROM correctionsWord WHERE voca_id = :vocabularyId")
    suspend fun getCorrectionsWordByVocabularyId(vocabularyId: Int): List<CorrectionsWord>
}