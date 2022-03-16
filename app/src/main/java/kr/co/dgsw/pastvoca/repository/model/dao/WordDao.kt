package kr.co.dgsw.pastvoca.repository.model.dao

import androidx.room.Dao
import androidx.room.Query
import kr.co.dgsw.pastvoca.base.BaseDao
import kr.co.dgsw.pastvoca.repository.model.dto.Word

@Dao
interface WordDao : BaseDao<Word> {
    @Query("SELECT * FROM word")
    suspend fun getAllWords(): List<Word>
}