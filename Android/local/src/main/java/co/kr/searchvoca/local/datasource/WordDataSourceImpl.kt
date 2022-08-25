package co.kr.searchvoca.local.datasource

import co.kr.searchvoca.data.datasouce.WordDataSource
import co.kr.searchvoca.data.model.WordResult
import co.kr.searchvoca.local.SearchVOCADatabase
import co.kr.searchvoca.local.entity.WordEntity
import co.kr.searchvoca.local.entity.toEntity
import co.kr.searchvoca.local.entity.toModel

class WordDataSourceImpl(db: SearchVOCADatabase) : WordDataSource {

    private val dao = db.wordDao()

    override suspend fun loadWords(): List<WordResult> =
        dao.loadWords().map(WordEntity::toModel)

    override suspend fun loadSearchHistory(): List<WordResult> =
        dao.loadWordsByVocabulary(co.kr.searchvoca.shared.domain.VocabularyId.SEARCH_HISTORY.ordinal).map(WordEntity::toModel)

    override suspend fun loadWordsByVocabulary(vocabularyId: Int): List<WordResult> =
        dao.loadWordsByVocabulary(vocabularyId).map(WordEntity::toModel)

    override suspend fun getSearchHistoryCount(): Int =
        dao.getWordCountByVocabulary(co.kr.searchvoca.shared.domain.VocabularyId.SEARCH_HISTORY.ordinal)

    override suspend fun getWordCount(vocabularyId: Int): Int =
        dao.getWordCountByVocabulary(vocabularyId)

    override suspend fun createWord(wordResult: WordResult) {
        dao.insert(wordResult.toEntity())
    }

    override suspend fun editWord(wordResult: WordResult) {
        dao.update(wordResult.toEntity())
    }

    override suspend fun changeWordVocabulary(ids: List<Int>, vocabularyId: Int) {
        dao.updateWordVocabulary(ids, vocabularyId)
    }

    override suspend fun deleteWord(wordResult: WordResult) {
        dao.delete(wordResult.toEntity())
    }

    override suspend fun deleteWords(ids: List<Int>) {
        dao.deletes(ids)
    }
}