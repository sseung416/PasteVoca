package kr.co.dgsw.pastvoca.repository.model.repository

import kr.co.dgsw.pastvoca.base.BaseRepository
import kr.co.dgsw.pastvoca.repository.model.dao.VocabularyDao
import kr.co.dgsw.pastvoca.repository.model.dto.Vocabulary

class VocabularyRepository(override val dao: VocabularyDao) : BaseRepository<VocabularyDao, Vocabulary>() {
    suspend fun getVocabularies() = dao.getVocabularies()

    suspend fun getVocabularyNames() = dao.getVocabularyNames()

    override suspend fun insert(obj: Vocabulary) = dao.insert(obj)

    override suspend fun update(obj: Vocabulary) = dao.update(obj)

    override suspend fun delete(obj: Vocabulary) = dao.delete(obj)
}