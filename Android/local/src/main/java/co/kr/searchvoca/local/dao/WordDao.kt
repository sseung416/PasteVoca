package co.kr.searchvoca.local.dao

import androidx.room.Dao
import androidx.room.Query
import co.kr.searchvoca.local.entity.WordEntity
import co.kr.searchvoca.shared.domain.VocabularyId

@Dao
interface WordDao : BaseDao<WordEntity> {

    @Query("SELECT * FROM word WHERE voca_id <> :searchHistoryVocabularyId")
    suspend fun loadWords(
        searchHistoryVocabularyId: Int = VocabularyId.SEARCH_HISTORY.ordinal
    ): List<WordEntity>

    @Query("SELECT * FROM word WHERE voca_id = :vocabularyId")
    suspend fun loadWordsByVocabulary(vocabularyId: Int): List<WordEntity>

    @Query("SELECT count(*) FROM word WHERE voca_id = :vocabularyId")
    suspend fun getWordCountByVocabulary(vocabularyId: Int): Int

    @Query("UPDATE word SET voca_id = :vocabularyId WHERE id IN(:ids)")
    suspend fun updateWordVocabulary(ids: List<Int>, vocabularyId: Int)

    @Query("DELETE FROM word WHERE id IN (:ids)")
    suspend fun deletes(ids: List<Int>)
}