package co.kr.dgsw.searchvoca.repository.model.repository

import co.kr.dgsw.searchvoca.base.BaseRepository
import co.kr.dgsw.searchvoca.repository.model.dao.WordDao
import co.kr.dgsw.searchvoca.repository.model.dto.Word

class WordRepository(override val dao: WordDao) : BaseRepository<WordDao, Word>() {
    suspend fun getAllWords() = dao.getAllWords()

    suspend fun getWordsByVocabulary(vocabularyId: Int) = dao.getWordsByVocabulary(vocabularyId)

    override suspend fun insert(obj: Word) = dao.insert(obj)

    override suspend fun update(obj: Word) = dao.update(obj)

    override suspend fun delete(obj: Word) = dao.delete(obj)
}