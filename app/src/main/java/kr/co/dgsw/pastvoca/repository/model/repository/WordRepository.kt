package kr.co.dgsw.pastvoca.repository.model.repository

import kr.co.dgsw.pastvoca.base.BaseRepository
import kr.co.dgsw.pastvoca.repository.model.dao.WordDao
import kr.co.dgsw.pastvoca.repository.model.dto.Word

class WordRepository(override val dao: WordDao) : BaseRepository<WordDao, Word>() {
    suspend fun getAllWords() = dao.getAllWords()

    override suspend fun insert(obj: Word) = dao.insert(obj)

    override suspend fun update(obj: Word) = dao.update(obj)

    override suspend fun delete(obj: Word) = dao.delete(obj)
}