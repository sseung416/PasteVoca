package co.kr.dgsw.searchvoca.datasource.model.repository

import co.kr.dgsw.searchvoca.datasource.model.dao.WordDao
import co.kr.dgsw.searchvoca.datasource.model.dto.Word

class WordRepository(override val dao: WordDao) : BaseRepository<WordDao, Word>() {
    suspend fun getAllWords() = dao.getAllWords()

    suspend fun getWordsByVocabulary(vocabularyId: Int) = dao.getWordsByVocabulary(vocabularyId)

    suspend fun updateWordVocabulary(ids: List<Int>, vocabularyId: Int) = dao.updateWordVocabulary(ids, vocabularyId)

    suspend fun delete(ids: List<Int>) = dao.delete(ids)

    override suspend fun insert(obj: Word) = dao.insert(obj)

    override suspend fun update(obj: Word) = dao.update(obj)

    override suspend fun delete(obj: Word) = dao.delete(obj)
}