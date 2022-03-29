package co.kr.dgsw.searchvoca.repository.model.repository

import co.kr.dgsw.searchvoca.base.BaseRepository
import co.kr.dgsw.searchvoca.repository.model.dao.VocabularyDao
import co.kr.dgsw.searchvoca.repository.model.dto.Vocabulary

class VocabularyRepository(override val dao: VocabularyDao) : BaseRepository<VocabularyDao, Vocabulary>() {
    suspend fun getVocabularies() = dao.getVocabularies()

    suspend fun getVocabularyNames() = dao.getVocabularyNames()

    override suspend fun insert(obj: Vocabulary) = dao.insert(obj)

    override suspend fun update(obj: Vocabulary) = dao.update(obj)

    override suspend fun delete(obj: Vocabulary) = dao.delete(obj)
}