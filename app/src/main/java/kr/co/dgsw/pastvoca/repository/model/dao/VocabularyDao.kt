package kr.co.dgsw.pastvoca.repository.model.dao

import androidx.room.Dao
import androidx.room.Query
import kr.co.dgsw.pastvoca.base.BaseDao
import kr.co.dgsw.pastvoca.repository.model.dto.Vocabulary
import kr.co.dgsw.pastvoca.repository.model.dto.VocabularyName

@Dao
interface VocabularyDao : BaseDao<Vocabulary> {
    @Query("SELECT * FROM vocabulary")
    suspend fun getVocabularies(): List<Vocabulary>

    @Query("SELECT id, name FROM vocabulary")
    suspend fun getVocabularyNames(): List<VocabularyName>
}