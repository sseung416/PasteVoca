package co.kr.searchvoca.local.dao

import androidx.room.Dao
import androidx.room.Query
import co.kr.searchvoca.local.entity.VocabularyEntity
import co.kr.searchvoca.shared.domain.VocabularyId

@Dao
interface VocabularyDao : BaseDao<VocabularyEntity> {

    @Query("SELECT * FROM vocabulary WHERE id <> :historyId")
    suspend fun loadVocabularies(historyId: Int = VocabularyId.SEARCH_HISTORY.ordinal): List<VocabularyEntity>
}